package com.vshop.veat.service;


import com.vshop.veat.entity.ProductCategory;

import java.util.Collection;
import java.util.List;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 14:27
 */
public interface CategoryService {
    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(Collection<Integer> categoryTypes);

    void save(ProductCategory productCategory);
}
