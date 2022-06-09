package cool.wangshuo.album.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * 评论信息表(AlbumDbReview)实体类
 *
 * @author wangsh
 * @since 2022-04-29 14:02:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemarkEntity implements Serializable {

    private static final long serialVersionUID = 762835919408676400L;

    /**
     * 评论编号、主键
     */
    private Integer remarkId;
    /**
     * 相册编号 外键
     */
    private Integer albumId;
    /**
     * 发布评论的用户ID 外键
     */
    private Integer userId;
    /**
     * 评论信息
     */
    private String remarkInfo;
    /**
     * 评论状态 评论状态 （0 等待审核 1 审核完成  ）
     */
    private Integer remarkStatue;
    /**
     * 评论时间
     */
    private Date commentDate;

}

