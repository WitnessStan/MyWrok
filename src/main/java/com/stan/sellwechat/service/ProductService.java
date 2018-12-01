package com.stan.sellwechat.service;

import com.stan.sellwechat.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);
}
