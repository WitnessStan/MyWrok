package com.stan.sellwechat.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    //买家姓名
    @NotBlank(message = "姓名必填")
    private String name;

    //买家电话
    @NotBlank(message = "手机号必填")
    private String phone;

    //买家地址
    @NotBlank(message = "地址必填")
    private String address;

    //买家openid
    @NotBlank(message = "openid必填")
    private String openid;

    //买家购物车
    @NotBlank(message = "购物车必填")
    private String items;
}
