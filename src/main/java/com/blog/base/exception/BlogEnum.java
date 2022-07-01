package com.blog.base.exception;
//定义用户操作失败（如密码输入错误等等
public enum BlogEnum {

    //第一个001:用户登录模块  第二个001：登录中验证码错误的情况
    USER_LOGIN_CODE("001-001","验证码错误"),
    USER_LOGIN_ACCOUNT("001-002","密码错误"),
    USER_VERIFY_PASS("001-003","旧密码错误"),
    USER_UPDATE("001-004","旧密码错误"),
    ARTICLE_ISOPEN("002-001","修改文章是否公开失败"),
    ARTICLE_PUNISH("002-002","发布文章失败!"),
    ARTICLE_DELETE("002-002","删除文章失败!");

    private String typeCode;//属于哪个模块下的操作失败code
    private String message;//具体的消息错误


       BlogEnum(String typeCode, String message) {
        this.typeCode = typeCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
