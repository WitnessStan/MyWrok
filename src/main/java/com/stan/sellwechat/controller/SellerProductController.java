package com.stan.sellwechat.controller;

import com.stan.sellwechat.domain.ProductCategory;
import com.stan.sellwechat.domain.ProductInfo;
import com.stan.sellwechat.enums.ProductStatusEnum;
import com.stan.sellwechat.enums.ResultEnum;
import com.stan.sellwechat.exceptions.SellException;
import com.stan.sellwechat.form.ProductForm;
import com.stan.sellwechat.service.CategoryService;
import com.stan.sellwechat.service.ProductService;
import com.stan.sellwechat.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "4") Integer pageSize
            , Map<String, Object> map) {
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize < 1) {
            pageSize = 4;
        }

        Page<ProductInfo> productInfoPage = productService.findAll(PageRequest.of(currentPage - 1, pageSize));
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", currentPage);
        map.put("pageSize", pageSize);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping("/off_sell")
    public ModelAndView offSell(@RequestParam String productId, Map<String, Object> map) {
        try {
            productService.offSell(productId);
        }catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        map.put("msg","");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/on_sell")
    public ModelAndView onSell(@RequestParam String productId, Map<String, Object> map) {
        try {
            productService.onSell(productId);
        }catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg","");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(required = false) String productId, Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    @PostMapping("/save")
    @Transactional
    public ModelAndView save(@Valid ProductForm productForm, BindingResult bindingResult, Map<String, Object> map) {
        //表单验证
        if(bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        //检查是新增还是更新
        ProductInfo productInfo = new ProductInfo();
        try {
            //id为空则为新增商品
            if(StringUtils.isEmpty(productForm.getProductId())) {
                BeanUtils.copyProperties(productForm, productInfo);
                productInfo.setProductId(KeyUtil.genUniqueKey());
                productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            }else {
                productInfo = productService.findOne(productForm.getProductId());
                BeanUtils.copyProperties(productForm, productInfo);
            }
            productService.save(productInfo);
        }catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
