package cool.wangshuo.album.mapper;

import cool.wangshuo.album.entity.AlbumUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户信息表：普通用户和管理员(AlbumDb_User)表数据库访问层
 *
 * @author wangsh
 * @since 2022-04-29 14:02:23
 */
@Mapper
public interface UserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    AlbumUserEntity queryById(Integer userId);


    List<AlbumUserEntity> queryAllByLimit(AlbumUserEntity entity);

    /**
     * 统计总行数
     *
     * @param album_UserEntity 查询条件
     * @return 总行数
     */
    long count(AlbumUserEntity album_UserEntity);

    /**
     * 新增数据
     *
     * @param album_UserEntity 实例对象
     * @return 影响行数
     */
    int insert(AlbumUserEntity album_UserEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlbumDb_User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AlbumUserEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlbumDb_User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AlbumUserEntity> entities);

    /**
     * 修改数据
     *
     * @param album_UserEntity 实例对象
     * @return 影响行数
     */
    int update(AlbumUserEntity album_UserEntity);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}

