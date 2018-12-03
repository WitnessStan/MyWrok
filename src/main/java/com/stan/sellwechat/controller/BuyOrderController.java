package com.stan.sellwechat.controller;

import com.stan.sellwechat.VO.ResultVO;
import com.stan.sellwechat.converter.OrderForm2OrderDTOConverter;
import com.stan.sellwechat.dto.OrderDTO;
import com.stan.sellwechat.enums.ResultEnum;
import com.stan.sellwechat.exceptions.SellException;
import com.stan.sellwechat.form.OrderForm;
import com.stan.sellwechat.service.OrderService;
import com.stan.sellwechat.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单,需要进行表单验证---> Valid 根据 OrderForm 中定义的规则利用BindingResult来进行验证
    @RequestMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建订单】，参数不正确，orderForm={}",orderForm);

            //throw new SellException(ResultEnum.PARAM_ERROR);

            //具体显示是什么异常
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】，购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO resultOrderDTO = orderService.createOrder(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", resultOrderDTO.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单详情
    @RequestMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openId, @RequestParam("orderid") String orderId) {
        return ResultVOUtil.success("test");
    }

    //订单列表
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openId, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        
        return ResultVOUtil.success("test");
    }

    //取消订单
}
