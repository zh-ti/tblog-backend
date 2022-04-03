package com.tian.tblog.enums;

public enum HttpStatusEnum {
    AuthenticationFail(401, "账号或密码错误"),
    UnAuthorization(405, "未登录或权限不足"),
    Ok(200, "ok");

    private int code;
    private String message;
    HttpStatusEnum(int status, String message){
        this.code = status;
        this.message = message;
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

    @Override
    public String toString() {
        return "HttpStatus{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
