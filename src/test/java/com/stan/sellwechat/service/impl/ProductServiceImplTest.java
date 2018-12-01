package com.stan.sellwechat.service.impl;

import com.stan.sellwechat.domain.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private  ProductServiceImpl productService;

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> infoPage = productService.findAll(pageRequest);
        System.out.println(infoPage.getTotalElements());
    }

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("12345");
        log.info(productInfo.toString());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setCategoryType(30);
        productInfo.setProductDescription("测试测试");
        productInfo.setProductIcon("测试");
        productInfo.setProductName("测试1");
        productInfo.setProductPrice(new BigDecimal(50.32));
        productInfo.setProductStatus(1);
        productInfo.setProductStock(1);
        ProductInfo productInfo1 = productService.save(productInfo);
        log.info(productInfo1.toString());
    }
}