package cool.wangshuo.album.model.vo;

import cool.wangshuo.album.entity.AlbumUserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangsh 前端实体类
 * @date 2022/6/23 14:38
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    /**
     * 用户昵称
     */
    private String username;
    /**
     * 用户帐号
     */
    private String userNum;
    /**
     * 用户密码
     */
    private String passwd;

    private String passwdRepeat;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户QQ
     */
    private String qqCode;


}
