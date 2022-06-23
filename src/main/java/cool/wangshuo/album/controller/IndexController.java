package cool.wangshuo.album.controller;

import cool.wangshuo.album.entity.AlbumUserEntity;
import cool.wangshuo.album.model.domain.CommonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 返回前端响应的网页 【拼接网页框架】 <br>
 * @author wangsh
 * @date 2022/5/9 14:23
 */

@Controller
@RequestMapping(produces="text/html;charset=UTF-8")
public class IndexController {

    private String uri;
    private AlbumUserEntity user;

    public IndexController() {

    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpSession session) {
        this.uri = request.getRequestURI();
        this.user = (AlbumUserEntity) session.getAttribute("user");
    }

    @RequestMapping(value = {
            "/",
            "/index.html"}
    )

    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/AlbumDetail.html")
    public String albumDetail() {
        return "AlbumDetail.html";
    }

    @RequestMapping(value = "/home/*")
    public String home() {

        // 自定义三级路由

        if (this.uri.endsWith("home.html")){
            return "home/index.html";
        }

        if (this.uri.endsWith("picture.html")){
            return "home/picture.html";
        }

        if (this.uri.endsWith("album.html")){
            return "home/album.html";
        }

        if (this.uri.endsWith("/") || this.uri.endsWith("/index.html")){
            return "home/index.html";
        }

        return "home/index.html";
    }


    @RequestMapping(value = "/admin/*")
    public String admin() {

        // 自定义三级路由

        if (this.uri.endsWith("admin.html")){
            return "admin/album.html";
        }

        if (this.uri.endsWith("user.html")){
            return "admin/user.html";
        }

        if (this.uri.endsWith("album.html")){
            return "admin/album.html";
        }

        if (this.uri.endsWith("picture.html")){
            return "admin/picture.html";
        }

        if (this.uri.endsWith("remark.html")){
            return "admin/remark.html";
        }

        if (this.uri.endsWith("/") || this.uri.endsWith("/index.html")){
            return "admin/index.html";
        }

        return "admin/index.html";
    }

}