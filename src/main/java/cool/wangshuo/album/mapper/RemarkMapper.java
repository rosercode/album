package cool.wangshuo.album.mapper;

import com.alibaba.fastjson.JSONObject;
import cool.wangshuo.album.entity.RemarkEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 评论信息表(AlbumDbReview)表数据库访问层
 *
 * @author wangsh
 * @since 2022-04-29 14:02:25
 */
@Mapper
public interface RemarkMapper {


    /**
     * 获取所有的评论列表
     * @return
     */
    List<JSONObject> queryAll();


    List<JSONObject> queryAllByLimit(@Param("remark") RemarkEntity remarkFilter, @Param("offset") Integer offset, @Param("length") Integer length);

    /**
     * 获取指定 ID 的评论信息
     * @param albumId
     * @return
     */
    RemarkEntity queryById(Integer albumId);


    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    List<RemarkEntity> queryByAlbumId(Integer page, Integer limit, Integer albumId);

    /**
     * 统计总行数
     *
     * @param remarkEntity 查询条件
     * @return 总行数
     */
    long count(RemarkEntity remarkEntity);

    /**
     * 新增数据
     *
     * @param remarkEntity 实例对象
     * @return 影响行数
     */
    int insert(RemarkEntity remarkEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlbumDbReview> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RemarkEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlbumDbReview> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RemarkEntity> entities);

    /**
     * 修改数据
     *
     * @param remarkEntity 实例对象
     * @return 影响行数
     */
    int update(RemarkEntity remarkEntity);

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 影响行数
     */
    int deleteById(Integer remarkId);

}

