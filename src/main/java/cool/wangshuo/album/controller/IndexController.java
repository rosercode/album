package cool.wangshuo.album.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 返回前端响应的网页 【拼接网页框架】 <br>
 * @author wangsh
 * @date 2022/5/9 14:23
 */

@Controller
@RequestMapping(produces="text/html;charset=UTF-8")
public class IndexController {

    @RequestMapping(value = "/")
    public String index12() {
        return "index.html";
    }

    @RequestMapping(value = "/index.html")
    public String index() {
        return "index.html";
    }


    @RequestMapping(value = "/AlbumDetail.html")
    public String albumDetail() {
        return "AlbumDetail.html";
    }

    @RequestMapping(value = "/home/home.html")
    public String home() {
        return "home/home.html";
    }

    @RequestMapping(value = "/admin/admin.html")
    public String admin() {
        return "admin/admin.html";
    }
}