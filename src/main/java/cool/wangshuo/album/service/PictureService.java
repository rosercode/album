package cool.wangshuo.album.service;

import cool.wangshuo.album.entity.AlbumPictureEntity;

import java.util.List;

/**
 * @author wangsh
 * @date 2022/5/3 13:34
 */

public interface PictureService {


    public Integer insert(AlbumPictureEntity picture);

    public AlbumPictureEntity queryById(Integer photoId, Integer photoStatue);

    public List<AlbumPictureEntity> queryByPage(Integer page, Integer limit, Integer albumId);

    public Integer update(AlbumPictureEntity picture);

    public List<AlbumPictureEntity> queryAll(AlbumPictureEntity picture);

    public Integer delete(Integer photoId);
}
