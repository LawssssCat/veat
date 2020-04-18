package com.vshop.veat.mapper;

import com.vshop.veat.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface ProductCategoryMapper extends MyBatisBaseDao<ProductCategory, Integer> {
    /**
     * 根据Type查找
     *
     * @param type
     */
    ProductCategory selectByProductCategoryType(@Param("productCategoryType") Integer type);

    List<ProductCategory> selectListProductCategoryByType(@Param("productCategroyTypeList") Collection<Integer> types);
}