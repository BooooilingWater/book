package cn.newjava.book.service;

import cn.newjava.book.bean.User;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * (User)表服务接口
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    User Login(User user);

    /**
     * 分页获取用户
     * @param page 页码
     * @param size 每页条数
     * @return
     */
    Page<User> getUsers(int page, int size);
}