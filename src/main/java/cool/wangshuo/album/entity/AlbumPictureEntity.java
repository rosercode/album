package cool.wangshuo.album.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 照片信息表(AlbumDbPicture)实体类
 *
 * @author wangsh
 * @since 2022-04-29 14:02:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumPictureEntity implements Serializable {
    private static final long serialVersionUID = -89070487192254949L;
    /**
     * 照片编号 主键
     */
    private Integer photoId;
    /**
     * 相册编号 外键
     */
    private Integer albumId;
    /**
     * 图片所属用户的ID 外键
     */
    private Integer photoUserId;
    /**
     * 照片名称
     */
    private String photoName;
    /**
     * 照片状态 1有效，0禁用 管理员设定
     */
    private Integer photoStatue;
    /**
     * 照片权限 1可访问，0不可访问 用户设定
     */
    private Integer photoRight;
    /**
     * 照片详情说明
     */
    private String photoIntro;
    /**
     * 照片路径
     */
    private String photoUrl;
}

