package cn.newjava.book.service;

import cn.newjava.book.bean.Book;
import cn.newjava.book.bean.vo.BookVo;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * (Book)表服务接口
 */
public interface BookService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Book queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Book> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    Book insert(Book book);

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    Book update(Book book);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @param size
     * @param bookName
     * @param categoryId
     * @return
     */
    Page<BookVo> queryByPage(int page, int size, String bookName, Integer categoryId);
}