package cn.newjava.book.controller;

import cn.newjava.book.bean.ApiResult;
import cn.newjava.book.bean.User;
import cn.newjava.book.service.UserService;
import com.github.pagehelper.Page;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * (User)表控制层
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 用户登录接口
     * @param user 登录的用户, 包括用户名和密码
     * @param request
     * @return
     */
    @RequestMapping("login")
    public ApiResult doLogin(User user, HttpServletRequest request) {
        User u = userService.Login(user);
        if (u != null) {
            // 登陆成功后将用户对象存入session中
            request.getSession().setAttribute("user", u);
            return ApiResult.success("登录成功");
        } else {
            return ApiResult.fail("用户名或密码错误");
        }
    }

    /**
     * 用户注册接口
     * @param user 注册的用户对象
     * @return 注册的用户
     */
    @RequestMapping("register")
    public ApiResult doRegister(@Validated User user) { // 通过@Validated进行字段校验
        user.setUsername(user.getUsername().trim());
        User u = userService.insert(user);
        return u == null ? ApiResult.fail("注册失败, 用户名已被使用") : ApiResult.success(user);
    }

    /**
     * 根据用户id更新用户信息的接口
     * @param user 更新的用户对象, 不为null的属性为要更新的
     * @param request
     * @return
     */
    @RequestMapping("update")
    public ApiResult updateUser(@Validated User user, HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        user.setId(u.getId());
        User update = userService.update(user);
        return update != null ? ApiResult.success(update) : ApiResult.fail("更新失败");
    }

    /**
     * 更新密码接口
     * @param password 新密码
     * @param oldPassword 旧密码
     * @param request 用以获取用户id
     * @return
     */
    @PostMapping("update/password")
    public ApiResult updatePassword(String password, String oldPassword, HttpServletRequest request) {
        if (password == null || password.length() > 20 || password.length() < 6)
            return ApiResult.fail("更新失败, 密码长度为6-20为位");
        User user = (User) request.getSession().getAttribute("user");
        if (user.getPassword().equals(oldPassword)) {
            User u = new User();
            u.setPassword(password);
            u.setId(user.getId());
            user = userService.update(u);
            request.getSession().setAttribute("user", user);
            return ApiResult.success("更新密码成功");
        }
        return ApiResult.fail("原密码不正确");
    }

}