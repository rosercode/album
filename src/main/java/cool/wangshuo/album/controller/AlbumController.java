package cool.wangshuo.album.controller;

import com.alibaba.fastjson.JSONObject;
import cool.wangshuo.album.AlbumApplication;
import cool.wangshuo.album.annotation.AdminAuth;
import cool.wangshuo.album.annotation.NeedLoginAuth;
import cool.wangshuo.album.entity.AlbumEntity;
import cool.wangshuo.album.entity.AlbumPictureEntity;
import cool.wangshuo.album.entity.AlbumUserEntity;
import cool.wangshuo.album.model.domain.CommonResponse;
import cool.wangshuo.album.service.AlbumService;
import cool.wangshuo.album.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsh
 * @date 2022/4/30 0:25
 */

@RestController
@RequestMapping(value = "/album")
@Slf4j
public class AlbumController {

    @Resource
    private AlbumService albumService;


    /**
     * 返回对象实例 在每一次请求到来时，完成实例的初始化
     */
    private CommonResponse response;

    /**
     * 当前请求所登录的用户
     */
    private AlbumUserEntity user;

    public AlbumController() {

    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpSession session) {
        this.response = new CommonResponse();
        this.user = (AlbumUserEntity) session.getAttribute("user");
    }


    /**
     * 添加一条相册信息 【需要登录】 <br>
     * bug：修改图片文件名的生成，图片名称后端生成，不能通过前端上传的数据来设定，因为可能重复。PS：最简单使用主键名称来 <br>
     * 下一步：增加一步数据校验 -- 对前端上传的图片进行安全性验证。<br>  <time datetime="2022-06-21 21:52:20">时间戳</time>
     *
     * @param file 上传的文件
     * @param album 相册的 id
     * @return
     */
    @PostMapping(value = "/insert")
    @NeedLoginAuth
    public CommonResponse insert(@RequestParam("imageFile") MultipartFile file, AlbumEntity album) {
        response.setCode(-1);

        // 对前端传来数据的检查
        if (album.getAlbumName() == null || album.getAlbumName().equals("")){
            response.setMessage("数据插入失败，相册名不能为空");
            return response;
        }


        final String albumFace = file.getOriginalFilename(); // 获取封面图片名称
        String imagePath = AlbumApplication.imagePace + File.separator + albumFace; // 图片的路径
        try {
            // 保存文件
            file.transferTo(new java.io.File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        album.setAlbumId(Integer.parseInt(CommonUtils.uuid())); // 创建主键ID
        album.setAlbumFace(albumFace);
        album.setAlbumStatue(0); // 相册状态 默认禁用

        album.setUserId(this.user.getUserId()); // PS：注意，相册的所属的设定只能通过在 session 中的数据来确定。
        System.out.println(album);
        Integer count = albumService.insert(album);
        if (count != -1) {
            response.setMessage("数据插入失败");
            return response;
        }
        response.setMessage("数据插入成功");
        log.info("客户端创建相册成功{},{},{}","1","1","1");
        return response;
    }


    /**
     * 分页查询 获取所有的相册信息  【此方法在 0.05 及以后的版本 被废弃】
     * param pmsAttendance 筛选条件
     *
     * @param page  分页对象
     * @param limit
     * @return
        @GetMapping(value = "/queryByPage")
        @Deprecated
        public List<JSONObject> queryByPage(Integer page, Integer limit) {
            return this.albumService.queryByPage(page, limit);
        }
     */

    /**
     *分页获取所有个人的相册 【此方法在 0.05 及以后的版本 被废弃】
     * @param page
     * @param limit
     * @return

        @Deprecated
        @GetMapping(value = "/queryPersonalByPage")
        public List<JSONObject> queryPersonalByPage(Integer page, Integer limit, HttpSession session) {
            return this.albumService.queryByPage(page, limit);
        }
     */


    /**
     * 获取符合条件的、所有的相册信息 <br>
     * 1. 有权限展示的数据 <br>
     * 2. 通过过滤的数据 <br>
     * PS：后面删除 source 这个参数，修改成通过 albumFilter 这个  JavaBean 过滤查询的数据
     *
     * @param albumFilter
     * @param source 来源 1 表示 前台 2 表示后台
     * @param pageNum 每页的页数
     * @param pageSize 页的大小
     * @return
     */
    @GetMapping(value = "/queryAll")
    public CommonResponse queryAll(AlbumEntity albumFilter,Integer source, Integer pageNum, Integer pageSize) {


        if(this.user == null){
            log.info("【未登录用户】查询所有相册列表信息");
        }else {
            log.info("【{}】用户名【{}】查询所有相册列表信息", this.user.getUsername(), this.user.getUserRight() == 1 ? "管理员": "一般用户");
        }

        /**
         * 如果没有登录，直接访问 公开的相册
         * 如果是管理员，则返回所有的用户信息
         * 如果是一般用户，限定查询条件
         * PS：相同要求的，还有照片信息
         * @date 2022-05-01 00:34:35
         */
        response.setCode(1);
        if (source == 1){
            albumFilter.setAlbumRight(1);
            albumFilter.setAlbumStatue(1);
            response.setData(this.albumService.queryAllByLimit(albumFilter, pageNum, pageSize));
            return response;
        }


        /**
         * 下面的是后台的处理
         * 1. 如果用户没有登录，要求用户登录
         * 2. 如果是一般用户登录，限定查询条件
         * 3. 管理员
         */

        // 1. 要求登录
        if (this.user == null){
            response.setCode(-1);
            response.setData(new ArrayList<>());
            return response;
        }

        // 后台一般用户
        if (this.user!=null && this.user.getUserRight() == 0){
            albumFilter.setUserId(this.user.getUserId());
            response.setData(this.albumService.queryAllByLimit(albumFilter, pageNum, pageSize));
            return response;
        }

        // 管理员
        response.setData(this.albumService.queryAllByLimit(null, pageNum, pageSize));
        return response;
    }


    /**
     * 更新相册的状态 审核相册： 审核中 -> 审核完成  或者  审核完成 -> 审核中
     * @param albumId
     * @return
     */
    @GetMapping(value = "/verifyAlbum")
    @AdminAuth
    public CommonResponse verifyAlbum(Integer albumId) {
        AlbumEntity album = this.albumService.queryById(albumId);
        album.setAlbumStatue(album.getAlbumStatue() == 1 ? 0 : 1);
        int count = albumService.update(album);
        response.setCode(-1);
        if (count != 1) {
            response.setMessage("数据更新失败");
        }
        response.setCode(1);
        response.setMessage("数据更新成功");
        return response;
    }

    /**
     * 删除相册信息 【管理员权限、图片拥有者权限】
     * @param albumId 相册的主键ID
     * @return
     */
    @GetMapping(value = "/delete")
    @NeedLoginAuth
    public CommonResponse delete(Integer albumId) {
        response.setCode(-1);
        int count = this.albumService.delete(albumId);
        if (count != 1){
            response.setMessage("删除失败");
            return response;
        }
        response.setCode(1);
        response.setMessage("删除完成");
        return response;
    }


    /**
     * 展示相册的封面图片 <br>
     * Bug1: [用户权限判断] 需要增加相册是否公开和审核通过的判断（后面修改） <br>
     * PS：严格不算 Bug，可以不修复，因为相册封面是不必隐藏的
     *
     * @param albumId
     * @param response
     * @param scale
     * @throws IOException
     */
    @GetMapping(value = "/face/{albumId}")
    public void showAlbumFace(@PathVariable Integer albumId, HttpServletResponse response, Float scale) throws IOException {

        /**
         * 流程
         * 1. 前端数据校验
         * 2. 用户权限检查 （划去）
         * 3. 业务逻辑处理
         *
         */

        if (albumId == null) {
            return;
        }
        AlbumEntity album = this.albumService.queryById(albumId);
        if (album == null) {
            return;
        }
        String imagePath = AlbumApplication.imagePace + File.separator + album.getAlbumFace();

        if (!new File(imagePath).exists()) {
            response.sendError(404);
        }
        CommonUtils.showPhoto(response, imagePath, scale);
    }
}
