package cool.wangshuo.album.service.imp;

import cool.wangshuo.album.entity.AlbumPictureEntity;
import cool.wangshuo.album.mapper.PictureMapper;
import cool.wangshuo.album.service.PictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangsh
 * @date 2022/4/30 1:08
 */
@Service
public class PictureServiceImp implements PictureService {

    @Resource
    private PictureMapper pictureMapper;

    @Override
    public Integer insert(AlbumPictureEntity picture) {
        return pictureMapper.insert(picture);
    }

    @Override
    public AlbumPictureEntity queryById(Integer photoId, Integer photoStatue) {
        return pictureMapper.queryById(photoId, photoStatue);
    }

    @Override
    public List<AlbumPictureEntity> queryByPage(Integer page, Integer limit, Integer albumId) {
        return this.pictureMapper.queryByPage(albumId);
    }

    @Override
    public Integer update(AlbumPictureEntity picture){
        return this.pictureMapper.update(picture);
    }

    @Override
    public List<AlbumPictureEntity> queryAll(AlbumPictureEntity picture, Integer pageNum, Integer pageSize) {
        int offset = (pageNum-1) * pageSize;
        return this.pictureMapper.queryAllByLimit(picture, offset, pageSize);
    }

    @Override
    public Integer delete(Integer photoId) {
        return this.pictureMapper.deleteById(photoId);
    }
}
