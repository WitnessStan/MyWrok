package com.stan.sellwechat.utils;

import com.stan.sellwechat.enums.CodeEnum;

/**
 * 定义泛型方法，其格式是：修饰符 <类型参数列表> 返回类型 方法名(形参列表) { 方法体 }。
 * 例如：public static <T, S> int func(List<T> list, Map<Integer, S> map) { ... }，其中T和S是泛型类型参数。
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
