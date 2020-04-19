package com.vshop.veat.controller;


import com.vshop.veat.entity.Product;
import com.vshop.veat.service.IProductCategoryService;
import com.vshop.veat.service.IProductService;
import com.vshop.veat.vo.JsonResult;
import com.vshop.veat.vo.ProductsWithCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 买家商品
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IProductCategoryService iProductCategoryService;

    @GetMapping("/list")
    public JsonResult list() {
        // 1. 查询所有的上架商品
        List<Product> allProducts = iProductService.findUpAll();

        // 2. 对传入的分类，并封装返回
        List<ProductsWithCategoryVo> productsWithCategoryVoList = iProductCategoryService.sortWithCategory(allProducts);

        return JsonResult.ok().data(productsWithCategoryVoList);
    }

}
