package com.stan.sellwechat.service;

import com.stan.sellwechat.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    //创建订单
    OrderDTO createOrder(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(String orderId);

    //查询用户订单列表
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    //取消订单
    OrderDTO cancelOrder(OrderDTO orderDTO);

    //完结订单
    OrderDTO finishOrder(OrderDTO orderDTO);

    //支付订单
    OrderDTO payOrder(OrderDTO orderDTO);
}
