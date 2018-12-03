package com.stan.sellwechat.repository;

import com.stan.sellwechat.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> products = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,products.size());
    }

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setCategoryType(30);
        productInfo.setProductDescription("测试测试");
        productInfo.setProductIcon("测试");
        productInfo.setProductName("测试1");
        productInfo.setProductPrice(new BigDecimal(50.32));
        productInfo.setProductStatus(1);
        productInfo.setProductStock(1);
        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }
}