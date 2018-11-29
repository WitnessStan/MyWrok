package com.stan.sellwechat.service;

import com.stan.sellwechat.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    ProductCategory save(ProductCategory productCategory);
}
