package cn.newjava.book.bean;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * (Borrowing)实体类

 */
@Data
public class Borrowing implements Serializable {
    private static final long serialVersionUID = 190495236662922890L;

    private Integer id;

    private Integer userId;

    private Integer bookId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getReturnTime() {
        long  i = 1000 * 60 * 60 * 24;
        return createTime != null ? new Date(createTime.getTime() + i * 60) : null;
    }
}