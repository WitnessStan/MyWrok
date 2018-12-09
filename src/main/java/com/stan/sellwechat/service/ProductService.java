package com.stan.sellwechat.service;

import com.stan.sellwechat.domain.ProductInfo;
import com.stan.sellwechat.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo findOne(String productId);

    //查询所有在架商品
    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);

    //增加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减少库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    void onSell(String productId);

    //下架
    void offSell(String productId);
}
