package cn.newjava.book.service.impl;

import cn.newjava.book.bean.Book;
import cn.newjava.book.bean.vo.BookVo;
import cn.newjava.book.mapper.BookMapper;
import cn.newjava.book.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Book)表服务实现类
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Book queryById(Integer id) {
        return this.bookMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Book> queryAllByLimit(int offset, int limit) {
        return this.bookMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book insert(Book book) {
        int i = this.bookMapper.insert(book);
        return i > 0 ? bookMapper.queryById(book.getId()) : null;
    }

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book update(Book book) {
        this.bookMapper.update(book);
        return this.queryById(book.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.bookMapper.deleteById(id) > 0;
    }

    @Override
    public Page<BookVo> queryByPage(int page, int size, String bookName, Integer categoryId) {
        PageHelper.startPage(page, size);
        if (bookName != null)
            bookName = "%" + bookName + "%";
        return bookMapper.findByPaging(bookName, categoryId);
    }
}