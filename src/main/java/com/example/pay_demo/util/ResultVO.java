package com.example.pay_demo.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:ljf
 * @date: 2019/12/15
 * @description:
 **/
@Data
public class ResultVO<T> implements Serializable {

    private boolean success;

    private String msg;

    private T Data;

    private Integer code;
}
