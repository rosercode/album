package cool.wangshuo.album.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息表：普通用户和管理员(AlbumDb_User)实体类
 *
 * @author wangsh
 * @since 2022-04-29 14:02:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumUserEntity implements Serializable {

    private static final long serialVersionUID = 403642089394289118L;

    /**
     * 用户编号 主键
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 用户状态 1有效，0禁用 默认为 1
     */
    private Integer userStatue;
    /**
     * 用户帐号
     */
    private String userNum;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 用户权限 1管理员，0用户
     * 默认为 0
     */
    private Integer userRight;
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
    /**
     * 用户备注
     */
    private String remark;


}

