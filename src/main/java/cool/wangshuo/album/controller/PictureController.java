package cool.wangshuo.album.controller;

import cool.wangshuo.album.AlbumApplication;
import cool.wangshuo.album.annotation.AdminAuth;
import cool.wangshuo.album.annotation.NeedLoginAuth;
import cool.wangshuo.album.entity.AlbumPictureEntity;
import cool.wangshuo.album.entity.AlbumUserEntity;
import cool.wangshuo.album.model.domain.CommonResponse;
import cool.wangshuo.album.service.PictureService;
import cool.wangshuo.album.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsh
 * @date 2022/4/30 1:07
 */

@RestController
@RequestMapping(value = "/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;

    /**
     * 返回对象实例 在每一次请求到来时，完成实例的初始化
     */
    private CommonResponse response;

    /**
     * 当前请求所登录的用户
     */
    private AlbumUserEntity user;

    public PictureController() {
    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpSession session) {
        this.response = new CommonResponse();
        this.user = (AlbumUserEntity) session.getAttribute("user");
    }

    /**
     * 上传图片、插入图片信息  【需要登录】
     * @param file  图片
     * @param albumId
     * @param photoIntro
     * @return
     */
    @PostMapping(value = "/insert")
    @NeedLoginAuth
    public CommonResponse insertPhoto(@RequestParam("imageFile") MultipartFile file, Integer albumId,String photoIntro, Integer photoRight) {
        response.setCode(-1);


        if (file == null || albumId == null || albumId == 0 || photoIntro == null || photoIntro.equals("")) {
            response.setMessage("内容不能为空");
            return response;
        }
        String imageName = file.getOriginalFilename();
        AlbumPictureEntity picture = new AlbumPictureEntity();
        picture.setPhotoId(Integer.parseInt(CommonUtils.uuid()));
        picture.setPhotoName(imageName);
        picture.setPhotoRight(photoRight);
        picture.setPhotoStatue(0);
        picture.setAlbumId(albumId);
        picture.setPhotoUserId(this.user.getUserId());
        picture.setPhotoIntro(photoIntro);
        System.out.println(picture);
        pictureService.insert(picture);

//        System.out.println(file);
//        System.out.println(albumId);
        String imagePath = AlbumApplication.imagePath + File.separator + imageName;
        try {
            // 保存文件
            file.transferTo(new java.io.File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setCode(1);
        response.setMessage("用户上传图片成功");
        log.info("用户上传图片成功，保存路径为 {}", imagePath);
        return response;
    }



    /**
     * 展示图片
     * @param photoId 图片的 ID
     * @param response http's response
     * @param scale 图片是否缩放
     * @throws IOException
     */
    @GetMapping(value = "/one")
    public void showPhoto(Integer photoId, HttpServletResponse response, Float scale) throws IOException {
        /**
         * @date 2022-05-01 04:09:02
         * 在展示图片前，要确定图片是否有权限被展示，分前台、后台
         * PS:实际情况下，前台是不会展示有问题（没有通过审核、没有公开、、）的图片的因为：在获取图片列表时，已经区分了前台和后台（图片列表已经经过过滤了）
         *
         * 1. 前台：用户没有公开、管理员审核没有公开不能展示、其它情况下可以展示
         * 2. 后台。
         *    2.1 图片拥有者的后台可以被展示
         *    2.2 管理员的后台可以被展示
         *    其它情况下不能展示
         */
        AlbumPictureEntity picture = pictureService.queryById(photoId,null);
        String filepath = new StringBuilder()
                .append(AlbumApplication.imagePath)
                .append(File.separator)
                .append(picture.getPhotoName())
                .toString();
        // 图片的路径信息

        File imageFile = new File(filepath);

        // 图片不存在
        if (!imageFile.exists()){
            this.response.setMessage("文件不存在");
            log.info("访问了不存在的图片");
            return;
        }

        CommonUtils.showPhoto(response,filepath,scale);
    }

    /**
     * 获取【展示】指定ID的图片信息
     * @param photoId
     * @return

        @GetMapping(value = "/queryById")
        public AlbumPictureEntity queryById(Integer photoId) {
            return this.pictureService.queryById(photoId,null);
        }

     */

    /**
     * 分页查询 获取指定相册的图片列表
     * @param page
     * @param limit
     * @param albumId
     * @return

        @GetMapping(value = "/queryByPage")
        public List<AlbumPictureEntity> queryByPage(Integer page, Integer limit, Integer albumId) {
            return this.pictureService.queryByPage(page, limit, albumId);
        }
     */


    /**
     * 获取所有的图片信息 <br>
     * 1. 不同身份的用户其能访问的资源不同 <br>
     * 2. 不同界面请求的响应不同
     * @param pictureFilter pictureFilter
     * @param source
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/queryAll")
    public CommonResponse queryAll(AlbumPictureEntity pictureFilter,Integer source, Integer pageNum, Integer pageSize) {
        response.setCode(-1);

        // 前台（这里不考虑用户是否登录） ，只能访问 【用户公开】 【管理员没有禁止】 的图片
        if (source == 1) {
            pictureFilter.setPhotoStatue(1);
            pictureFilter.setPhotoRight(1);
            response.setData(this.pictureService.queryAll(pictureFilter,pageNum,pageSize));
            return response;
        }

        // 后台
        // 没有登录 -- 要求登录
        if (this.user == null) {
            response.setData(new ArrayList<>());
            return response;
        }

        // 如果已经登录，权限是一般用户
        if (this.user.getUserRight() == 0) {
            pictureFilter.setPhotoUserId(this.user.getUserId());
            response.setData(this.pictureService.queryAll(pictureFilter,pageNum,pageSize));
            return response;
        }

        // 如果是管理员,就不进行过滤
        response.setData(this.pictureService.queryAll(null,pageNum,pageSize));
        return response;
    }

    /**
     * 更新相册的状态（管理员审核相册）： 审核中 -> 审核完成  或者  审核完成 -> 审核中
     * @param photoId
     * @return
     */
    @GetMapping(value = "/updateStatus")
    @AdminAuth
    public CommonResponse updateStatus(Integer photoId) {

        AlbumPictureEntity picture = this.pictureService.queryById(photoId,null);
        if (picture == null){
            response.setMessage("数据查询失败");
            return response;
        }
        picture.setPhotoStatue(picture.getPhotoStatue() == 1 ? 0 : 1);
        response.setCode(-1);
        if (pictureService.update(picture) != 1) {
            response.setMessage("数据更新失败");
        }
        response.setCode(1);
        response.setMessage("数据更新成功");
        log.info("更新图片信息");
        return response;
    }


    /**
     * 删除图片信息 【管理员权限、图片拥有者权限】
     * @param photoId 图片的主键ID
     * @return
     */
    @GetMapping(value = "/delete")
    @AdminAuth
    public CommonResponse delete(Integer photoId) {

        response.setCode(-1);
        int count = this.pictureService.delete(photoId);
        if (count != 1){
            response.setMessage("删除失败");
            return response;
        }
        log.info("photoId为 {} 的照片信息被删除",photoId);
        response.setCode(1);
        response.setMessage("删除完成");
        log.info("删除图片信息");
        return response;
    }
}
