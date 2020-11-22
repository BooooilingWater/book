package cn.newjava.book.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * (Admin)实体类

 */
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = -36092566733016637L;
    
    private Integer id;
    
    private String name;
    
    private String password;
    
    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}