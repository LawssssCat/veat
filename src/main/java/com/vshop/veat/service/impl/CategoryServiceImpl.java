package com.vshop.veat.service.impl;

import com.vshop.veat.entity.ProductCategory;
import com.vshop.veat.mapper.ProductCategoryMapper;
import com.vshop.veat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 14:29
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper ;

    @Override
    public ProductCategory findOne(Integer id) {
        // TODO 校验
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryMapper.selectAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(Collection<Integer> categoryTypes) {
        return productCategoryMapper.selectListProductCategoryByType(categoryTypes);
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryMapper.insert(productCategory);
    }
}
