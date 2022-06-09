package cool.wangshuo.album.service;

import com.alibaba.fastjson.JSONObject;
import cool.wangshuo.album.entity.RemarkEntity;

import java.util.List;

/**
 * @author wangsh
 * @date 2022/5/3 13:35
 */

public interface RemarkService {

    public Integer insertRemark(RemarkEntity remark);

    public RemarkEntity queryById(Integer remarkId);

    public List<RemarkEntity> queryByAlbumId(Integer page, Integer limit, Integer albumId);

    public List<JSONObject> queryAll();

    public List<JSONObject> queryAll(RemarkEntity remark);

    public Integer updateRemark(RemarkEntity remark);

    public Integer deleteRemark(Integer remarkId);
}
