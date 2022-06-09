package cool.wangshuo.album.annotation;

import java.lang.annotation.*;

/**
 * 标记需要登录的接口
 * 拥有这个注解的方法接口，需要登录 <br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLoginAuth {

}
