package cn.newjava.book.controller;

import cn.newjava.book.bean.ApiResult;
import cn.newjava.book.bean.Book;
import cn.newjava.book.bean.vo.BookVo;
import cn.newjava.book.service.BookService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Book)表控制层
 */
@RestController
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Book selectOne(Integer id) {
        return this.bookService.queryById(id);
    }

    /**
     * 用户分页查询书籍
     * @param page 页码
     * @param limit 每页条数
     * @param bookName 筛选条件: 图书名字
     * @param categoryId 筛选条件: 书籍列别id
     * @return layui能识别的列表
     */
    @RequestMapping("user/book/search")
    public Object searchBook(int page,  int limit, String bookName, Integer categoryId) {
        page = Math.max(page, 1);
        limit = limit < 1 ? 10: limit;
        Page<BookVo> bookVoPage = bookService.queryByPage(page, limit, bookName, categoryId);
        Map<String, Object> result = new HashMap<>();
        result.put("data",bookVoPage.getResult());
        result.put("code",0);
        result.put("count",bookVoPage.getTotal());
        result.put("msg","");
        return result;
    }

    /**
     * 添加书籍接口
     * @param book 书籍对象
     * @return
     */
    @RequestMapping("admin/book/add")
    public ApiResult addBook(Book book) {
        Book i = bookService.insert(book);
        return i == null ? ApiResult.fail("添加失败") : ApiResult.success(i);
    }

    /**
     * 管理员书籍分页查询接口
     * @param page 页面
     * @param limit 每页条数
     * @param bookName 筛选条件: 图书名字
     * @param categoryId 筛选条件: 书籍列别id
     * @return layui能识别的列表
     */
    @RequestMapping("admin/book/search")
    public Object searchBook2(int page,  int limit, String bookName, Integer categoryId) {
        return searchBook(page, limit, bookName, categoryId);
    }

}