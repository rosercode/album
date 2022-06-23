package cool.wangshuo.album.mapper;

import com.alibaba.fastjson.JSONObject;
import cool.wangshuo.album.entity.AlbumEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 相册(AlbumDbAlbum)表数据库访问层
 *
 * @author wangsh
 * @since 2022-04-29 14:02:25
 */
@Mapper
public interface AlbumMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param albumId 主键
     * @return 实例对象
     */
    AlbumEntity queryById(Integer albumId);


    /**
     * 获取所有公开的相册
     * @return
     */
    List<JSONObject> queryAllPublic();

    /**
     *
     * @return
     */
    List<AlbumEntity> queryAll();


    /**
     * 有限制性的查询数据
     * @param albumEntityFilter
     * @param offset
     * @param length
     * @return
     */
    List<JSONObject> queryAllByLimit(
            @Param("album") AlbumEntity albumEntityFilter,
            @Param("offset") Integer offset,
            @Param("length") Integer length
    );

    /**
     * 统计总行数
     *
     * @param albumEntity 查询条件
     * @return 总行数
     */
    long count(AlbumEntity albumEntity);

    /**
     * 新增数据
     *
     * @param albumEntity 实例对象
     * @return 影响行数
     */
    int insert(AlbumEntity albumEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlbumDbAlbum> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AlbumEntity> entities);


    /**
     * 修改数据
     *
     * @param albumEntity 实例对象
     * @return 影响行数
     */
    int update(AlbumEntity albumEntity);

    /**
     * 通过主键删除数据
     *
     * @param albumId 主键
     * @return 影响行数
     */
    int deleteById(Integer albumId);

}

