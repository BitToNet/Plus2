package com.example.plus2.demos.mvp2.bean;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-30   10:43
 * desc   :
 */
public class BaseEntity{
    private int code;
    private boolean success;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
