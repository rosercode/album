package cool.wangshuo.album.annotation;

import java.lang.annotation.*;

/**
 * 需要管理员权限 【管理员表明是已经登录的】
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminAuth {

}
