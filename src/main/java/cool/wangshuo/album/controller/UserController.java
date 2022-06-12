package cool.wangshuo.album.controller;

import cool.wangshuo.album.annotation.AdminAuth;
import cool.wangshuo.album.annotation.NeedLoginAuth;
import cool.wangshuo.album.entity.AlbumUserEntity;
import cool.wangshuo.album.model.domain.CommonResponse;
import cool.wangshuo.album.service.UserService;
import cool.wangshuo.album.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wangsh
 * @date 2022/4/29 13:48
 */

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    private HttpServletRequest request;
    private HttpSession session;


    /**
     * 返回对象实例 在每一次请求到来时，完成实例的初始化
     */
    private CommonResponse response;


    private AlbumUserEntity user;


    public UserController() {
    }


    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpSession session) {
        this.response = new CommonResponse();
        this.session = session;   // 获取请求的 session
        this.request = request;
        this.user = (AlbumUserEntity) session.getAttribute("user");
    }


    /**
     * 用户登录 Api 接口
     * @param user
     * @return
     */
    @RequestMapping(value = "/login.api")
    public CommonResponse login(AlbumUserEntity user) {
        log.info("客户端请求登录");
        response.setCode(-1);

        if (user.getUserNum() == null || user.getUserNum().equals("") || user.getUserPwd() == null || user.getUserPwd().equals("")) {
            response.setMessage("登录信息不能为空");
            return response;
        }

        // 账号、密码强度检测

        List<AlbumUserEntity> userEntities = userService.queryAllByLimit(user);
        if (userEntities.size()!=1){
            response.setMessage("用户名或者密码错误");
            return response;
        }

//        AlbumUserEntity user1 =  userService.queryOneByUserId(1692201871); // 测试用户
        response.setCode(1);
        AlbumUserEntity user1 = userEntities.get(0);
        HttpSession session = this.request.getSession(true); // 如果没有
        session.setAttribute("user", user1);  // 保存用户的信息到 session 中

        if (user1.getUserRight() == 1){
            response.setMessage("管理员登录成功,正在跳转页面 ... ");
            response.setData(1);
            return response;
        }
        response.setData(0);
        response.setMessage("用户登录成功,正在跳转页面 ... ");

        return response;
    }

    /**
     * 用户注册 Api 接口
     * @param nickname  昵称
     * @param account   账号
     * @param passwd1   第一次的密码
     * @param passwd2   第二次的密码
     * @param phone     手机号码
     * @param address   地址信息
     * @param qqCode    QQ号码
     * @return
     */
    @RequestMapping(value = "/register.api")
    public CommonResponse register(String nickname,String account,String passwd1, String passwd2, String phone, String address, String qqCode) {
        log.info("客户端请求注册账号");

        AlbumUserEntity user = new AlbumUserEntity();
        response.setCode(-1);

        if (nickname == null || nickname.equals("") || passwd1 == null || passwd1.equals("") || passwd2 == null || passwd2.equals("") || phone == null || phone.equals("") || qqCode == null || qqCode.equals(" ")) {
            response.setMessage("注册失败，信息不能为空");
            return response;
        }

        if (!passwd1.equals(passwd2)) {
            response.setMessage("注册失败，两次密码不一致");
            return response;
        }

        Integer id = Integer.parseInt(CommonUtils.uuid());
        user.setUserId(id);          // 生成主键 ID
        user.setUsername(nickname);  // 设置用户名
        user.setUserNum(account);    // 设置账号
        user.setPhone(phone);        // 设置手机号
        user.setAddress(address);
        user.setQqCode(qqCode);      // 设置 QQ 号
        user.setUserPwd(passwd1);    // 设置密码
        user.setUserStatue(1);       // 设置用户的状态 管理员 或者 一般用户
        user.setUserRight(0);        // 设置用户的权限 可使用，或者不可使用

        response.setCode(1);
        response.setMessage("注册完成,正在跳转到的登录界面......");
        userService.insert(user);
        return response;
    }


    /**
     * 获取所有的注册用户 【需要管理员权限】
     * @return
     */
    @AdminAuth
    @RequestMapping(value = "/findAll")
    public List<AlbumUserEntity> findAll(){
        log.info("客户端 获取所有用户信息");
        return userService.queryAllByLimit(null);
    }

    /**
     * 删除指定用户ID的用户 【需要登录】 【需要管理员权限】
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete")
    @AdminAuth
    public CommonResponse deleteUser(Integer userId) {
        response.setCode(-1);

        if (this.user.getUserRight() == 0){
            response.setMessage("用户没有权限删除");
            return response;
        }

        log.info("客户端【{}】删除 userId 为 {} 的用户","", userId);
        userService.delete(userId);
        return response;
    }

}
