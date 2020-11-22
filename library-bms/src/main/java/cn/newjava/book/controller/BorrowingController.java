package cn.newjava.book.controller;

import cn.newjava.book.bean.ApiResult;
import cn.newjava.book.bean.Borrowing;
import cn.newjava.book.bean.User;
import cn.newjava.book.service.BorrowingService;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * (Borrowing)表控制层
 */
@RestController
@RequestMapping()
public class BorrowingController {
    /**
     * 服务对象
     */
    @Resource
    private BorrowingService borrowingService;


    /**
     * 用户借书接口i
     * @param bookId 所借书籍的id
     * @param request 用以获取借书的用户id
     * @return
     */
    @RequestMapping("user/borrowing/add")
    public ApiResult add(int bookId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Borrowing borrowing = new Borrowing();
            borrowing.setUserId(user.getId());
            borrowing.setBookId(bookId);
            int i = borrowingService.insert(borrowing);
            if (i == 1)
                return ApiResult.success(borrowing);
            if (i == -1)
                return ApiResult.fail("添加失败");
            if (i == -2)
                return ApiResult.fail("哎呀, 该书已被借走");
        }
        return ApiResult.notAllow();
    }

    /**
     * 用户还书接口
     * @param bookId 要归还的书籍id
     * @param request 用以获取还书的用户id
     * @return
     */
    @RequestMapping("user/borrowing/return")
    public ApiResult returnBook(int bookId, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            Integer userId = user.getId();
            Borrowing borrowing = new Borrowing();
            borrowing.setUserId(userId);
            borrowing.setBookId(bookId);
            boolean b = borrowingService.delete(borrowing);
            return b ? ApiResult.success(true) : ApiResult.fail("归还失败");
        }
        return ApiResult.notAllow();
    }

}