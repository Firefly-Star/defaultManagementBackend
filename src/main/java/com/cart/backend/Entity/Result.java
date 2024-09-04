package com.cart.backend.Entity;

public class Result {
    private Integer status;
    private String message;
    private Object data;

    public Result(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public static Result Success(Object data)
    {
        return new Result(1, "success", data);
    }

    public static Result Fail(String message)
    {
        return new Result(-1, message, null);
    }
}
