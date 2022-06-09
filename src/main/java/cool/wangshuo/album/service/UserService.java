package cool.wangshuo.album.service;

import cool.wangshuo.album.entity.AlbumUserEntity;

import java.util.List;

/**
 * @author wangsh
 * @date 2022/5/3 13:31
 */

public interface UserService {


    public int insert(AlbumUserEntity user);

    public AlbumUserEntity queryOneByUserId(Integer userId);

    public List<AlbumUserEntity> queryAllByLimit(AlbumUserEntity user);

    public Integer update(AlbumUserEntity user);

    public Integer delete(Integer userId);
}
