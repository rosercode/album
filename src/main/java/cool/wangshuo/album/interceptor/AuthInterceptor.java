package cool.wangshuo.album.interceptor;


import com.google.gson.Gson;
import cool.wangshuo.album.annotation.AdminAuth;
import cool.wangshuo.album.annotation.NeedLoginAuth;
import cool.wangshuo.album.entity.AlbumUserEntity;
import cool.wangshuo.album.model.domain.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 权限、登录验证拦截器 <br>
 * 不过滤静态资源，只针对所有的后端接口进行过滤
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 拦截器所做的事情：
         * @date 2022-04-30 03:50:09
         * 1. 检查是否登录
         * 2. 检查访问的资源，用户是否具有相关的权限
         */

        /**
         * @date 2022-05-01 20:44:17
         * 在这里，首先第一步对需要登录但是，没有登录的接口进行排除
         */

        // 日志输出
        AlbumUserEntity user = (AlbumUserEntity) request.getSession(true).getAttribute("user");
        String userIdentity = "";
        if (user == null) {
            userIdentity = "未登录用户";
        } else {
            userIdentity = user.getUsername();
        }
        
        log.info("客户端【用户名:{}】请求资源 {}",userIdentity, request.getRequestURI());

        // 拦截后端接口
        if (handler instanceof HandlerMethod) {
            NeedLoginAuth auth1 = ((HandlerMethod) handler).getMethod().getAnnotation(NeedLoginAuth.class);
            AdminAuth auth2 = ((HandlerMethod) handler).getMethod().getAnnotation(AdminAuth.class);
            if (auth1 != null || auth2 != null) {
                log.info("用户请求的资源需要登录");
                /**
                 * 请求的资源包含有 NeedLoginAuth 这个注解
                 * 下一步判断用户是否完成登录
                 */
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                CommonResponse response1 = new CommonResponse();
                response1.setCode(-2);
                if (user == null) {
                    log.info("用户请求的接口需要登录，但是 当前用户尚未登录");
                    // 访问的资源需要登录，请跳转到登录界面
                    response1.setMessage("请先完成登录");
                    response.getWriter().println(new Gson().toJson(response1));
                    return false;
                }
                if (auth2!=null && user.getUserRight() == 0 ){
                    log.info("用户请求的接口需要管理员泉下，但是 当前用户不是管理员");
                    response1.setMessage("没有权限访问该接口（No permission to access this interface）");
                    response.getWriter().println(new Gson().toJson(response1));
                    return false;
                }
                log.info("用户请求的资源需要登录，当前用户已经登录，用户名为 {}，通过", user.getUsername());
                return true;
            } else {
                log.info("用户请求的资源不需要登录");
                return true;
            }
        }
        // 拦截网页
        // 管理员后台 和 一般用户后台
        if (request.getRequestURI().startsWith("/home") && (user == null || user.getUserRight() != 0)){
            log.info("未登录用户或者管理员，尝试访问个人后台被拒绝");
            CommonResponse response1 = new CommonResponse();
            response1.setCode(-2);
            response1.setMessage("未登录用户或者管理员，不能访问个人后台");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println(new Gson().toJson(response1));
            return false;
        }

        if (request.getRequestURI().startsWith("/admin") && (user == null || user.getUserRight() != 1)){
            log.info("未登录用户或者普通用户，尝试访问管理员后台被拒绝");
            CommonResponse response1 = new CommonResponse();
            response1.setCode(-2);
            response1.setMessage("未登录用户或者普通用户，不能访问管理员后台");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println(new Gson().toJson(response1));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}


