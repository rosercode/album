package cool.wangshuo.album.service.imp;

import com.alibaba.fastjson.JSONObject;
import cool.wangshuo.album.entity.RemarkEntity;
import cool.wangshuo.album.mapper.RemarkMapper;
import cool.wangshuo.album.service.RemarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangsh
 * @date 2022/4/30 1:07
 */
@Service
public class RemarkServiceImp implements RemarkService {

    @Resource
    private RemarkMapper remarkMapper;

    @Override
    public Integer insertRemark(RemarkEntity remark){
        return remarkMapper.insert(remark);
    }

    @Override
    public RemarkEntity queryById(Integer remarkId){
        return this.remarkMapper.queryById(remarkId);
    }

    @Override
    public List<RemarkEntity> queryByAlbumId(Integer page, Integer limit, Integer albumId) {
        return remarkMapper.queryByAlbumId(page,limit,albumId);
    }

    @Override
    public List<JSONObject> queryAll() {
        return remarkMapper.queryAll();
    }

    @Override
    public List<JSONObject> queryAll(RemarkEntity remarkFilter, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null){
            return remarkMapper.queryAllByLimit(remarkFilter, null, null);
        }
        int offset = (pageNum-1) * pageSize;
        return remarkMapper.queryAllByLimit(remarkFilter,offset, pageSize);
    }

    @Override
    public Integer updateRemark(RemarkEntity remark){
        return remarkMapper.update(remark);
    }

    @Override
    public Integer deleteRemark(Integer remarkId){
        return remarkMapper.deleteById(remarkId);
    }
}
