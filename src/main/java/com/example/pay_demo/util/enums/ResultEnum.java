package com.example.pay_demo.util.enums;


import lombok.Getter;

@Getter
public enum ResultEnum {


    USER_NOT_EXISTS(10000,"用户不存在"),
    USER_PWD_ERROR(10001,"密码错误"),
    USER_LOGIN_SUCCESS(10002,"登陆成功");


    private int code;
    private String msg ;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
