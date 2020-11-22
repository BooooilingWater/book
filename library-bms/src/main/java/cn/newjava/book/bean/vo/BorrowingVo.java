package cn.newjava.book.bean.vo;

import cn.newjava.book.bean.Book;
import cn.newjava.book.bean.Borrowing;
import lombok.Data;

import java.util.Date;

@Data
public class BorrowingVo extends Borrowing {
    private Book book;
    private String username;
    private String bookName;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return book == null ? null : book.getName();
    }
}
