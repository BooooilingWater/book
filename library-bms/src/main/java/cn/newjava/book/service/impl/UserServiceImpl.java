package cn.newjava.book.service.impl;

import cn.newjava.book.bean.User;
import cn.newjava.book.mapper.UserMapper;
import cn.newjava.book.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        // 查询用户名是否已被注册
        User u = new User();
        u.setUsername(user.getUsername());
        List<User> users = userMapper.queryAll(u);
        if (users.size() < 1) { // 没有则进行插入
            int i = this.userMapper.insert(user);
            return i > 0 ? userMapper.queryById(user.getId()) : null;
        }
        return null;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        int i = this.userMapper.update(user);
        return i > 0 ? this.queryById(user.getId()) : null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userMapper.deleteById(id) > 0;
    }

    @Override
    public User Login(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty())
            return null;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty())
            return null;
        List<User> users = userMapper.queryAll(user);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public Page<User> getUsers(int page, int size) {
        PageHelper.offsetPage((page - 1) * size, size);
        Page<User> users = userMapper.queryAllUser();
        return users;
    }
}