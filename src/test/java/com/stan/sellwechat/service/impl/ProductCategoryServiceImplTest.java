package com.stan.sellwechat.service.impl;

import com.stan.sellwechat.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void findOne() {
        ProductCategory one = productCategoryService.findOne(1);
        Assert.assertNotNull(one);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = productCategoryService.findAll();
        Assert.assertNotNull(all);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categories = productCategoryService.findByCategoryTypeIn(Arrays.asList(10, 30, 50, 67, 90));
        Assert.assertNotEquals(0, categories.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = productCategoryService.save(new ProductCategory("service测试", 25));
        Assert.assertNotNull(productCategory);
    }
}