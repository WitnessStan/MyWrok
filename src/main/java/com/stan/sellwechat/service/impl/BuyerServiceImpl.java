package com.stan.sellwechat.service.impl;

import com.stan.sellwechat.dto.OrderDTO;
import com.stan.sellwechat.enums.ResultEnum;
import com.stan.sellwechat.exceptions.SellException;
import com.stan.sellwechat.service.BuyerService;
import com.stan.sellwechat.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return checkOwnOrder(openId,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO = checkOwnOrder(openId,orderId);

        if(orderDTO == null) {
            log.error("【取消订单】，订单不存在，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancelOrder(orderDTO);
    }

    //检查openid和订单openid的一致性
    public OrderDTO checkOwnOrder(String openId, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);

        if(orderDTO == null) {
            return null;
        }

        if(!orderDTO.getBuyerOpenid().equals(openId)) {
            log.error("【查询订单】订单openid不一致，openid={}，orderDTO={}",openId,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }

        return orderDTO;
    }
}
