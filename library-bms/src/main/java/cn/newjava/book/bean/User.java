package cn.newjava.book.bean;

import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * (User)实体类

 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 396525154344131416L;
    //用户id
    private Integer id;

    private String truename;


    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 6, message = "用户名长度为1-6")
    private String username;

    @Length(min = 6,max = 20)
    private String password;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String phone;

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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