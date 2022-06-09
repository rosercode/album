package cool.wangshuo.album.service.imp;

import cool.wangshuo.album.mapper.UserMapper;
import cool.wangshuo.album.entity.AlbumUserEntity;
import cool.wangshuo.album.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangsh
 * @date 2022/4/29 13:51
 */

@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserMapper userMapper;


    public UserServiceImp() {

    }

    @Override
    public int insert(AlbumUserEntity user){
        return userMapper.insert(user);
    }

    @Override
    public AlbumUserEntity queryOneByUserId(Integer userId){
        return userMapper.queryById(userId);
    }

    @Override
    public List<AlbumUserEntity> queryAllByLimit(AlbumUserEntity user){
        return userMapper.queryAllByLimit(user);
    }

    @Override
    public Integer update(AlbumUserEntity user){
        return this.userMapper.update(user);
    }

    @Override
    public Integer delete(Integer userId){
        return userMapper.deleteById(userId);
    }

}
