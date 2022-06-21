package cool.wangshuo.album.service.imp;

import com.alibaba.fastjson.JSONObject;
import cool.wangshuo.album.entity.AlbumEntity;
import cool.wangshuo.album.mapper.AlbumMapper;
import cool.wangshuo.album.service.AlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangsh
 * @date 2022/4/30 0:26
 */

@Service
public class AlbumServiceImp implements AlbumService {

    @Resource
    private AlbumMapper albumMapper;



    @Override
    public Integer insert(AlbumEntity album) {
        return albumMapper.insert(album);
    }

    @Override
    public AlbumEntity queryById(Integer albumId) {
       return albumMapper.queryById(albumId);
    }


    /**
     * 限制过滤条件、查询符合条件的相册列表
     * @param album
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<JSONObject> queryAllByLimit(AlbumEntity album, Integer pageNum, Integer pageSize){
        int offset = (pageNum-1) * pageSize;
        return albumMapper.queryAllByLimit(album, offset, pageSize);
    }

    @Override
    public List<JSONObject> queryByPage(Integer page, Integer limit) {
        if (page == null && limit == null) {
            return albumMapper.queryAllPublic();
        }
        Integer fromIndex = (page - 1) * limit; // 起始位置
        return albumMapper.queryAllPublic();
    }

    @Override
    public List<AlbumEntity> queryAll() {
        return albumMapper.queryAll();
    }

    @Override
    public Integer update(AlbumEntity album) {
        return this.albumMapper.update(album);
    }

    @Override
    public Integer delete(Integer albumId) {
        return this.albumMapper.deleteById(albumId);
    }
}
