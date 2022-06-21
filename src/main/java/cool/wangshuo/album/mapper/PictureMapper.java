package cool.wangshuo.album.mapper;

import cool.wangshuo.album.entity.AlbumPictureEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 照片信息表(AlbumDbPicture)表数据库访问层
 *
 * @author wangsh
 * @since 2022-04-29 14:02:25
 */

@Mapper
public interface PictureMapper {


    /**
     * 通过ID查询单条数据
     * @param pictureId 主键
     * @return 实例对象
     */
    AlbumPictureEntity queryById(Integer photoId,Integer photoStatue);


    /**
     *
     * @param albumId
     * @return
     */
    List<AlbumPictureEntity> queryByPage(Integer albumId);


    /**
     *
     * @return
     */
    List<AlbumPictureEntity> queryAll();


    List<AlbumPictureEntity> queryAllByLimit(
                @Param("picture") AlbumPictureEntity pictureFilter,
                @Param("offset") Integer offset,
                @Param("length") Integer length
    );


    /**
     * 统计总行数
     *
     * @param albumPictureEntity 查询条件
     * @return 总行数
     */
    long count(AlbumPictureEntity albumPictureEntity);

    /**
     * 新增数据
     *
     * @param albumPictureEntity 实例对象
     * @return 影响行数
     */
    int insert(AlbumPictureEntity albumPictureEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlbumDbPicture> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AlbumPictureEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlbumDbPicture> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AlbumPictureEntity> entities);

    /**
     * 修改数据
     *
     * @param albumPictureEntity 实例对象
     * @return 影响行数
     */
    int update(AlbumPictureEntity albumPictureEntity);

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 影响行数
     */
    int deleteById(Integer photoId);

}

