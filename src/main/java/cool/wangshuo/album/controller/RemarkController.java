package cool.wangshuo.album.controller;

import com.alibaba.fastjson.JSONObject;
import cool.wangshuo.album.annotation.AdminAuth;
import cool.wangshuo.album.annotation.NeedLoginAuth;
import cool.wangshuo.album.entity.AlbumUserEntity;
import cool.wangshuo.album.entity.RemarkEntity;
import cool.wangshuo.album.model.domain.CommonResponse;
import cool.wangshuo.album.service.RemarkService;
import cool.wangshuo.album.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wangsh
 * @date 2022/4/30 1:07
 */

@RestController
@RequestMapping(value = "/remark")
@Slf4j
public class RemarkController {

    @Resource
    private RemarkService remarkService;

    /**
     * 返回对象实例 在每一次请求到来时，完成实例的初始化
     */
    private CommonResponse response;

    private HttpSession session;
    private AlbumUserEntity user;

    public RemarkController() {
    }


    @ModelAttribute
    public void setReqAndRes(HttpSession session) {
        this.response = new CommonResponse();
        this.session = session;
        this.user = (AlbumUserEntity) session.getAttribute("user");

    }


    /**
     * 分页查询、根据相册的 ID 获取评论列表
     * @param page
     * @param limit
     * @param albumId
     * @return

        <pre>
        @GetMapping(value = "/queryByPage")
        public List<RemarkEntity> queryByAlbumId(Integer page, Integer limit, Integer albumId) {
            log.info("客户端获取相册ID为 {} 的评论列表",albumId);
            return this.remarkService.queryByAlbumId(page, limit, albumId);
        }
        </pre>
     */



    /**
     * 获取所有的评论信息
     * bug1： 登录用户无法获取自己的评论信息列表 (前端不需要这个判断) 不一定是 Bug ：因为用户发布的评论，本身就不能够管理
     * @param remarkFilter 过滤器
     * @param pageNum 第几页 下标从 1 开始
     * @param pageSize 每页的大小
     * @return
     */
    @GetMapping(value = "/queryAll")
    public List<JSONObject> queryAll(RemarkEntity remarkFilter, Integer pageNum, Integer pageSize) {
        log.info("客户端 获取所有的评论信息");

        // 没有登录或者是一般用户的情况下，只能访问公开【审核通过】的评论
        if (this.user == null || this.user.getUserRight() == 0){
            remarkFilter.setRemarkStatue(1);
            return this.remarkService.queryAll(remarkFilter,pageNum,pageSize);
        }

        // 如果是管理员,就不进行过滤
        return this.remarkService.queryAll(null, pageNum,pageSize);
    }

    /**
     * 发布评论信息 【需要登录】
     * @param remark
     * @return
     */
    @GetMapping(value = "/expressRemark")
    @NeedLoginAuth
    public CommonResponse insertRemark(RemarkEntity remark){
        response.setCode(-1);

        if (this.user == null){
            response.setMessage("发布评论请先完成登录");
            return response;
        }

        response.setCode(-1);
        // 数据校验
        if (remark.getRemarkInfo() == null || remark.equals("")){
            response.setMessage("评论内容不能为空");
        }
        remark.setRemarkId(Integer.parseInt(CommonUtils.uuid()));
        remark.setUserId(this.user.getUserId());
        remark.setRemarkStatue(0);
        log.info("客户端插入评论信息，用户名为 {}，评论的内容为 {}",user.getUserNum(),remark.getRemarkInfo());
        response.setMessage("评论发布完成，等待管理员审核");
        remarkService.insertRemark(remark);
        return response;
    }

    /**
     * 更新评论信息 【需要管理权限】
     * @param remark
     * @return
     */
    @RequestMapping(value = "update")
    @AdminAuth
    public CommonResponse updateRemark(RemarkEntity remark) {
        log.info("客户端【管理员】获取更新评论信息");
        Integer count = remarkService.updateRemark(remark);
        return response;
    }

    /**
     * 更新评论的状态： 审核中 -> 审核完成  或者  审核完成 -> 审核中
     * @param remarkId
     * @return
     */
    @GetMapping(value = "/updateStatus")
    @AdminAuth
    public CommonResponse updateRemarkStatus(Integer remarkId) {
        RemarkEntity remark = this.remarkService.queryById(remarkId);
        if (remark == null) {
            response.setMessage("数据查询失败");
            return response;
        }
        remark.setRemarkStatue(remark.getRemarkStatue() == 1 ? 0 : 1);
        response.setCode(-1);
        if (remarkService.updateRemark(remark) != 1) {
            response.setMessage("数据更新失败");
        }
        response.setCode(1);
        response.setMessage("数据更新成功");
        return response;
    }


    /**
     * 删除评论 【需要管理员权限】 <br>
     * PS:评论的拥有者不能删除评论，只有管理员可以删除 <br>
     *
     * @param remarkId
     * @return
     */
    @RequestMapping(value = "delete")
    @AdminAuth
    public CommonResponse deleteRemark(Integer remarkId){

        response.setCode(-1);
        Integer count =  remarkService.deleteRemark(remarkId);
        if (count != 1){
            response.setMessage("删除失败");
            return response;
        }
        response.setCode(1);
        response.setMessage("删除成功");
        return response;
    }
}
