package com.stan.sellwechat.repository;

import com.stan.sellwechat.domain.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory category = repository.findById(1).get();
        log.info(category.toString());
    }

    @Test
    public void findAllTest() {
        List<ProductCategory> categories = repository.findAll();
        Assert.assertNotNull(categories);
    }

    @Test
    @Transactional     //junit中的Transactional意思是执行完毕就回滚，不持久化
    public void saveTest(){
        ProductCategory category = new ProductCategory("junit测试3",40);
        ProductCategory category1 = repository.save(category);
        Assert.assertNotNull(category1);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> integers = Arrays.asList(30, 20, 90);
        List<ProductCategory> categories = repository.findByCategoryTypeIn(integers);
        Assert.assertNotEquals(0,categories.size());
    }

    @Test
    public void updateTest(){
        ProductCategory category = repository.findById(2).get();
        category.setCategoryName("改动测试2");
        ProductCategory result = repository.save(category);
        Assert.assertEquals(category,result);
    }
}