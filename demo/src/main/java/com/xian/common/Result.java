package com.xian.common;

/**
 * @author lgx
 */
public class Result<T> {

    private String code;

    private String msg;

    private T data;

    private Result() {
    }

    public static <T> Result<T> of(int result) {
        return result > 0 ? success() : error("操作错误");
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode("0");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("0");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode("500");
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(String code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
