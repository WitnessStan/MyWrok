package com.stan.sellwechat.service.impl;

import com.stan.sellwechat.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerServiceImplTest {

    @Autowired
    private BuyerServiceImpl buyerService;

    @Test
    public void findOrderOne() {
        OrderDTO orderDTO = buyerService.findOrderOne("1101110", "1543731635292107121");
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void cancelOrder() {
        buyerService.cancelOrder("00001","00000");
    }

    @Test
    public void checkOwnOrder() {
        buyerService.checkOwnOrder("1101110","1543731635292107121");
    }
}