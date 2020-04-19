package com.vshop.veat.mapper;

import com.vshop.veat.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类目表 Mapper 接口
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    @MapKey("productCategoryType")
    Map<Integer, ProductCategory> selectByProductCategoryTypeList(@Param("productCategoryTypes") Collection<Integer> categoryTypes);
}
