package com.stan.sellwechat.service.impl;

import com.stan.sellwechat.domain.OrderDetail;
import com.stan.sellwechat.dto.OrderDTO;
import com.stan.sellwechat.enums.OrderStatusEnum;
import com.stan.sellwechat.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1101110";

    private final String ORDER_ID = "1497183332311989948";

    @Test
    public void createOrder() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("廖师兄");
        orderDTO.setBuyerAddress("幕课网");
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1234568");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.createOrder(orderDTO);
        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1543731635292107121");
        log.info(orderDTO.toString());
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancelOrder() {
        OrderDTO orderDTO = orderService.findOne("1543731635292107121");
        OrderDTO result = orderService.cancelOrder(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finishOrder() {
        OrderDTO orderDTO = orderService.findOne("1543731635292107121");
        OrderDTO result = orderService.finishOrder(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void payOrder() {
        OrderDTO orderDTO = orderService.findOne("1543731635292107121");
        OrderDTO result = orderService.payOrder(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}