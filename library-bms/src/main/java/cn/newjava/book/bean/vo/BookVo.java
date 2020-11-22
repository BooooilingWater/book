package cn.newjava.book.bean.vo;

import cn.newjava.book.bean.Book;
import lombok.Data;

@Data
public class BookVo extends Book {
    private Integer borrowingId;
    // 可借/不可借
    private String status;
    // 分类名字
    private String categoryName;

    public Integer getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(Integer borrowingId) {
        this.borrowingId = borrowingId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatus() {
        if (borrowingId == null)
            return "可借";
        return "不可借";
    }

}
