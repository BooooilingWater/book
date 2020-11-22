package cn.newjava.book.bean;

import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * (Category)实体类

 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 834851743274698035L;

    private Integer id;

    @NotBlank(message = "")
    @Length(min = 1, max = 10)
    private String name;

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
}