package cn.newjava.book.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * (Book)实体类

 */
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 359221890498816920L;
    //书籍id
    private Integer id;
    //书名
    private String name;
    //作者名字
    private String author;
    // 出版社
    private String publish;
    //类别id
    private Integer categoryId;
    //价格
    private Object price;
    //书籍的简介
    private String introduction;
    //书籍封面
    private String cover;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}