package com.family.fmlbase.base;

public enum CommonCode {
    CLIENT_ERROR(400000, "客户端错误"),
    PARAM_ERROR(400001, "参数错误"),
    LOGIN_ERROR(400002, "登录异常"),
    NO_DATA(400003, "没有数据"),
    NO_PAGE(400004, "404页面"),
    NO_PERMISSION(400005, "没有操作权限"),
    VERIFY_CODE_ERROR(401000, "验证码验证失败"),
    VERIFY_GRAPH_ERROR(401005, "验证码验证失败"),
    NEED_GRAPH_CODE_ERROR(401001, "需要图片验证码"),
    NEED_MSG_CODE_ERROR(401002, "需要短信验证码"),
    NEED_BIND_PHONE(401003, "需要绑定手机号"),
    VERIFY_CODE_NEED_TIME(401004, "验证码倒计时状态"),
    SYSTEM_ERROR(500000, "服务异常"),
    TOKEN_EXPIRED(500001, "token已过期");

    private Integer code;
    private String message;

    private CommonCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
