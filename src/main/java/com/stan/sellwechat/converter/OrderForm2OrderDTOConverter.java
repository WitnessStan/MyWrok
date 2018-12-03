package com.stan.sellwechat.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stan.sellwechat.domain.OrderDetail;
import com.stan.sellwechat.dto.OrderDTO;
import com.stan.sellwechat.enums.ResultEnum;
import com.stan.sellwechat.exceptions.SellException;
import com.stan.sellwechat.form.OrderForm;
import javassist.bytecode.stackmap.BasicBlock;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO converter(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        Gson gson = new Gson();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = null;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e){
            log.error("【对象转换错误】，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
