package cn.newjava.book.bean;

import lombok.Data;

@Data
public class ApiResult {
    private Integer status;
    private String msg;
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiResult() {

    }

    public ApiResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ApiResult success(Object data) {
        return new ApiResult(200, "success", data);
    }

    public static ApiResult error() {
        return new ApiResult(500, "error", null);
    }

    public static ApiResult notAllow() {
        return new ApiResult(411, "not allow", null);
    }

    public static ApiResult fail(Object data) {
        return new ApiResult(201, "fail", data);
    }
}
