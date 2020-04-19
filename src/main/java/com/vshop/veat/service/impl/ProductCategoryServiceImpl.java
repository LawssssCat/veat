package com.vshop.veat.service.impl;

import com.vshop.veat.entity.Product;
import com.vshop.veat.entity.ProductCategory;
import com.vshop.veat.mapper.ProductCategoryMapper;
import com.vshop.veat.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vshop.veat.vo.ProductVo;
import com.vshop.veat.vo.ProductsWithCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
@Slf4j
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductsWithCategoryVo> sortWithCategory(List<Product> products) {
        Map<Integer, List<Product>> productMap = products.stream()
                // 按类型分类
                .collect(Collectors.groupingBy(Product::getProductCategoryType));

        // 数据库查找相关的类型
        Map<Integer, ProductCategory> categoryMap = productCategoryMapper.selectByProductCategoryTypeList(productMap.keySet());

        //  把全部信息按类型封装返回

        List<ProductsWithCategoryVo> result =
                // 映射成封装类型
                productMap.keySet().stream()
                        .map((categoryKey) -> {
                            ProductCategory category = categoryMap.get(categoryKey);
                            // TODO 如果没有，就弄个空的先
                            if (null == category) {
                                category = new ProductCategory();
                            }
                            // 封装
                            return new ProductsWithCategoryVo(
                                    ProductVo.buildList(productMap.get(categoryKey)),
                                    category.getProductCategoryName(),
                                    categoryKey
                            );
                        })
                        // 返回数组类型
                        .collect(Collectors.toList());
        return result;
    }
}
