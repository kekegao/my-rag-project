package com.example.demo.dto;

public class LoginResponse {

    private int code;
    private String message;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(int code, String message, String token) {
        this.code = code;
        this.message = message;
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // 便捷静态方法
    public static LoginResponse success(String token) {
        return new LoginResponse(200, "登录成功", token);
    }

    public static LoginResponse fail(String message) {
        return new LoginResponse(401, message, null);
    }
}
