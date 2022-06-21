package cool.wangshuo.album.service;

import com.alibaba.fastjson.JSONObject;
import cool.wangshuo.album.entity.AlbumEntity;

import java.util.List;

/**
 * @author wangsh
 * @date 2022/5/3 13:33
 */

public interface AlbumService {

    public Integer insert(AlbumEntity album);

    public AlbumEntity queryById(Integer albumId);

    public List<JSONObject> queryAllByLimit(AlbumEntity album, Integer pageNum, Integer pageSize);

    public List<JSONObject> queryByPage(Integer page, Integer limit);

    public List<AlbumEntity> queryAll();

    public Integer update(AlbumEntity album);

    public Integer delete(Integer albumId);
}
