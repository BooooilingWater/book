package cn.newjava.book.service;

import cn.newjava.book.bean.Borrowing;
import cn.newjava.book.bean.vo.BorrowingVo;

import java.util.List;

/**
 * (Borrowing)表服务接口
 */
public interface BorrowingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Borrowing queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Borrowing> queryAllByLimit(int offset, int limit);

    /**
     * 根据条件查询所有的借书记录
     * @param borrowing 查询条件, 为null时表示查询全部
     * @return
     */
    List<BorrowingVo> queryAll(Borrowing borrowing);

    /**
     * 新增数据
     *
     * @param borrowing 实例对象
     * @return 实例对象
     */
    int insert(Borrowing borrowing);

    /**
     * 修改数据
     *
     * @param borrowing 实例对象
     * @return 实例对象
     */
    Borrowing update(Borrowing borrowing);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过用户id和书籍id删除借书记录
     * @param borrowing 包含用户id和书籍id
     * @return 删除成功true, 失败false
     */
    boolean delete(Borrowing borrowing);
}