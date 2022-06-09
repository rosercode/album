package cool.wangshuo.album.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  CommonResponse http 通用返回模型类 <br>
 * 
 * @date 2022/4/15 12:35
 * @author wangsh
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {

    /**
     * 业务状态码：
     * - 表示成功 -1 <br>
     * - 表示失败 <br>
     * 0 权限原因
     * 1 需要登录
     * 2 其它原因
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据:相当于原本 http 响应数据包中的 响应实体
     */
    private Object data;


}
