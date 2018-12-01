package com.stan.sellwechat.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;
}
