package cn.newjava.book.controller;

import cn.newjava.book.bean.ApiResult;
import cn.newjava.book.bean.Category;
import cn.newjava.book.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Category)表控制层
 */
@RestController
@RequestMapping("admin/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 新增一个列别
     * @param category 类别对象, 包括编号id和名字name
     * @return
     */
    @RequestMapping("add")
    public ApiResult add(@Validated Category category) {
        Category insert = categoryService.insert(category);
        return insert == null ? ApiResult.fail("添加失败") : ApiResult.success(insert);
    }

    /**
     * 分页获取已有类别
     * @param page 页码
     * @param limit 每页条数
     * @return layui识别的分页对象
     */
    @RequestMapping("list")
    public Object getByPage(Integer page, Integer limit) {
        page = page == null ? 1 : Math.max(page, 1);
        limit = limit == null || limit < 1 ? 10 : limit;
        List<Category> all = categoryService.queryAllByLimit(0, 100);
        List<Category> categories = categoryService.queryAllByLimit((page - 1) * limit, limit);
        Map<String, Object> result = new HashMap<>();
        result.put("data", categories); // 列表
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", all.size()); // 总数
        return result;
    }

    /**
     * 更新类别的名字
     * @param id 要更新的类别的id
     * @param name 新的名字
     * @return 更新后的类别对象
     */
    @RequestMapping("update")
    public ApiResult updateName(int id, String name) {
        if (name == null || name.trim().isEmpty())
            return ApiResult.fail("分类名称不能为空");
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        Category update = categoryService.update(category);
        return update == null ? ApiResult.fail("更新失败"): ApiResult.success(category);
    }
}