package cool.wangshuo.album.annotation;

import java.lang.annotation.*;

/**
 * 标记需要登录的接口 <br>
 * 拥有这个注解的方法接口，需要登录 <br>
 *
 *
 * 权限说明
 * 1. 不需要登录即可访问
 * 2. 需要登录，但是不要求是管理员权限
 * 3. 需要登录，要求是管理员权限（用于管理员权限已经是登录用户的身份了）
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLoginAuth {

}
