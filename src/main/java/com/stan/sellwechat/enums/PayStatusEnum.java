package com.stan.sellwechat.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0,"等待付款"),
    SUCCESS(1,"付款成功")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
