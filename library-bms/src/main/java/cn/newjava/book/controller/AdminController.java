package cn.newjava.book.controller;

import cn.newjava.book.bean.*;
import cn.newjava.book.bean.vo.BorrowingVo;
import cn.newjava.book.service.AdminService;
import cn.newjava.book.service.BorrowingService;
import cn.newjava.book.service.CategoryService;
import cn.newjava.book.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Admin)表控制层
 *
 * @author 陈华强
 * @since 2019-10-22 13:13:08
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private BorrowingService borrowingService;

    @Resource
    private UserService userService;

    @RequestMapping("")
    public String toLogin1() {
        return "redirect:/admin/index.html";
    }

    @RequestMapping("/")
    public String toLogin2() {
        return "redirect:/admin/index.html";
    }

    /**
     * 页面路由, 跳转到相应的模板html页面
     * @param page 页面名字
     * @param model 视图
     * @return
     */
    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page, Model model) {
        if (page == null || page.trim().isEmpty())
            return "admin/index";
        switch (page) {
            case "addBook.html":
                List<Category> categories = categoryService.queryAllByLimit(0, 100);
                model.addAttribute("list", categories);
                return "admin/addBook";
            case "addCategory.html":
                return "admin/addCategory";
            case "addUser.html":
                return "admin/addUser";
            case "books.html":
                List<Category> list = categoryService.queryAllByLimit(0, 100);
                model.addAttribute("list", list);
                return "admin/books";
            case "borrowings.html":
                return "admin/borrowings";
            case "home.html":
                return "admin/home";
            case "password.html":
                return "admin/password";
            case "users.html":
                return "admin/users";
            default:
                return "admin/index";
        }
    }

    /**
     * 管理员登录接口
     * @param admin 管理员对象, 包括用户名和密码
     * @param request
     * @return
     */
    @PostMapping("/post/login")
    @ResponseBody
    public ApiResult doLogin(Admin admin, HttpServletRequest request) {
        Admin a = adminService.login(admin);
        if (a != null) {
            // 登录成功将管理员对象存入session中
            request.getSession().setAttribute("admin", admin);
            return ApiResult.success("登录成功");
        }
        return ApiResult.fail("登录失败, 账号或密码错误 !");
    }

    /**
     * 管理员更新密码接口
     * @param password 新密码
     * @param oldPassword 旧密码
     * @param request
     * @return
     */
    @PostMapping("update/password")
    @ResponseBody
    public ApiResult updatePassword(String password, String oldPassword, HttpServletRequest request) {
        if (password == null || password.length() > 20 || password.length() < 4)
            return ApiResult.fail("更新失败, 密码长度为6-20为位");
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin.getPassword().equals(oldPassword)) {
            Admin u = new Admin();
            u.setPassword(password);
            u.setId(admin.getId());
            admin = adminService.update(u);
            request.getSession().setAttribute("admin", admin);
            return ApiResult.success("更新密码成功");
        }
        return ApiResult.fail("原密码不正确");
    }

    /**
     * 管理员退出接口
     * @param request
     * @return 重定向到登陆页面
     */
    @RequestMapping("/logout/out")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/admin/index.html";
    }

    @RequestMapping("borrowing/list")
    @ResponseBody
    public Object getBorrowings(Integer page, Integer limit) {
        page = page == null || page < 1 ? 1 : page;
        limit = limit == null || limit < 1 ? 10 : limit;
        List<BorrowingVo> borrowingVos = borrowingService.queryAll(new Borrowing());
        int fromIndex = (page - 1) * limit;
        int toIndex = fromIndex + limit;
        toIndex = toIndex >= borrowingVos.size() ? borrowingVos.size() - 1 : toIndex;
        List<BorrowingVo> list = borrowingVos.subList(fromIndex, toIndex);
        Map<String, Object> result = new HashMap<>();
        result.put("data", list);
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", borrowingVos.size());
        return result;
    }

    /**
     * 分页获取用户列表
     * @param page 页码
     * @param limit 每页条数
     * @return data:用户列表, code:状态码/成功是0, count: 用户总数
     */
    @RequestMapping("user/list")
    @ResponseBody
    public Object getUserByPage(Integer page, Integer limit) {
        page = page == null || page < 1 ? 1 : page;
        limit = limit == null || limit < 1 ? 10 : limit;
        Page<User> users = userService.getUsers(page, limit);
        Map<String, Object> result = new HashMap<>();
        result.put("data", users.getResult());
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", users.getTotal());
        return result;
    }

    /**
     * 更新用户的用户名和邮箱信息
     * @param user 必须要有用户的id才能更新成功
     * @return 成功则返回更新后的信息
     */
    @PostMapping("update/user")
    @ResponseBody
    public ApiResult updateUserMsg(@Validated User user) {
        if (user == null)
            return ApiResult.error();
        user.setPassword(null);
        User update = userService.update(user);
        return update == null ? ApiResult.fail("更新失败") : ApiResult.success(update);
    }

    /**
     * 根据用户id来删除用户
     * @param id 用户id
     * @return true. false
     */
    @RequestMapping("delete/user")
    @ResponseBody
    public ApiResult deleteUserById(int id) {
        boolean b = userService.deleteById(id);
        return b ? ApiResult.success("true") : ApiResult.fail("删除失败");
    }

    @RequestMapping("delete/category")
    @ResponseBody
    public ApiResult deleteUserById1(int id) {
        boolean b = categoryService.deleteById(id);
        return b ? ApiResult.success("true") : ApiResult.fail("删除失败");
    }

}