package com.stan.sellwechat.controller;

import com.stan.sellwechat.VO.ProductInfoVO;
import com.stan.sellwechat.VO.ProductVO;
import com.stan.sellwechat.VO.ResultVO;
import com.stan.sellwechat.domain.ProductCategory;
import com.stan.sellwechat.domain.ProductInfo;
import com.stan.sellwechat.service.CategoryService;
import com.stan.sellwechat.service.ProductService;
import com.stan.sellwechat.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * RestController注解 == @Controller+@ResponseBody(返回json)
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        List<ProductInfo> productInfoList = productService.findUpAll();
        Set<Integer> categoryTypeSet = new HashSet<>();

        for(ProductInfo productInfo : productInfoList){
            categoryTypeSet.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(new ArrayList<Integer>(categoryTypeSet));

        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
