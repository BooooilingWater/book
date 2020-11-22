package cn.newjava.book.controller;

import cn.newjava.book.bean.Borrowing;
import cn.newjava.book.bean.Category;
import cn.newjava.book.bean.User;
import cn.newjava.book.bean.vo.BorrowingVo;
import cn.newjava.book.service.BorrowingService;
import cn.newjava.book.service.CategoryService;
import cn.newjava.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户页面跳转的controller
 */
@Controller
@RequestMapping("user")
public class HtmlController {

    @Autowired
    BorrowingService borrowingService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping("home")
    public String toIndex() {
        return "user/home";
    }

    /**
     * 用户退出登录
     * @param request
     * @return 重定向到登陆页面
     */
    @RequestMapping("logout")
    public String doLogout(HttpServletRequest request) {
        request.getSession().invalidate(); // 销毁session
        return "redirect:../index.html";
    }

    @RequestMapping("borrowing")
    public String toBorrowing(HttpServletRequest request) {
        return "user/borrowing";
    }

    @RequestMapping("borrowingHistory")
    public String toBorrowsHistory(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Borrowing borrowing = new Borrowing();
            borrowing.setUserId(user.getId());
            List<BorrowingVo> list = borrowingService.queryAll(borrowing);
            model.addAttribute("list", list);
            return "user/borrowingHistory";
        }
        return "redirect:../";
    }

    @RequestMapping("findBook")
    public String toFindBook(Model model) {
        List<Category> list = categoryService.queryAllByLimit(0, 100);
        model.addAttribute("list", list);
        return "user/findBook";
    }

    @RequestMapping("returnBook")
    public String toReturnBook() {
        return "user/returnBook";
    }

    @RequestMapping("center")
    public String toCenter(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            User query = userService.queryById(user.getId());
            request.getSession().setAttribute("user", query);
            model.addAttribute("user", query);
            return "user/center";
        }
        return "redirect:../index.html";
    }

    @RequestMapping("password")
    public String toPassword(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "user/password";
        }
        return "redirect:../index.html";
    }
}
