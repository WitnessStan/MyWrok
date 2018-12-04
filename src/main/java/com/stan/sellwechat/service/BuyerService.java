package com.stan.sellwechat.service;

import com.stan.sellwechat.dto.OrderDTO;

public interface BuyerService {

    public OrderDTO findOrderOne(String openId, String orderId);

    public OrderDTO cancelOrder(String openId, String orderId);
}
