package cn.newjava.book.service.impl;

import cn.newjava.book.bean.Borrowing;
import cn.newjava.book.bean.vo.BorrowingVo;
import cn.newjava.book.mapper.BorrowingMapper;
import cn.newjava.book.service.BorrowingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Borrowing)表服务实现类
 */
@Service("borrowingService")
@Transactional
public class BorrowingServiceImpl implements BorrowingService {
    @Resource
    private BorrowingMapper borrowingMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Borrowing queryById(Integer id) {
        return this.borrowingMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Borrowing> queryAllByLimit(int offset, int limit) {
        return this.borrowingMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param borrowing 查询条件
     * @return 对象列表
     */
    @Override
    public List<BorrowingVo> queryAll(Borrowing borrowing) {
        return this.borrowingMapper.queryAll(borrowing);
    }

    /**
     * 新增数据
     *
     * @param borrowing 实例对象
     * @return 1为添加成功, -1添加失败, -2 数已被借走
     */
    @Override
    public int insert(Borrowing borrowing) {
        // 先查询借书表中是否有该书
        Borrowing b = borrowingMapper.queryByBookId(borrowing.getBookId());
        // 没有则说明该书还未被借走, 进行插入, 有就取消插入
        if (b == null) {
            int i = borrowingMapper.insert(borrowing);
            return i > 0 ? 1 : -1;
        } else {
            return -2;
        }
    }

    /**
     * 修改数据
     *
     * @param borrowing 实例对象
     * @return 实例对象
     */
    @Override
    public Borrowing update(Borrowing borrowing) {
        this.borrowingMapper.update(borrowing);
        return this.queryById(borrowing.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.borrowingMapper.deleteById(id) > 0;
    }

    @Override
    public boolean delete(Borrowing borrowing) {
        int i = borrowingMapper.deleteByUserIdAndBookId(borrowing.getUserId(), borrowing.getBookId());
        return i > 0;
    }
}