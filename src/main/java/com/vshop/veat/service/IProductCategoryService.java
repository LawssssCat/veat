package com.vshop.veat.service;

import com.vshop.veat.entity.Product;
import com.vshop.veat.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vshop.veat.vo.ProductsWithCategoryVo;

import java.util.List;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
public interface IProductCategoryService extends IService<ProductCategory> {

    /**
     * 对传入的产品分类，并封装返回
     *
     * @param products 未分类的产品
     * @return 安排分类后，按类型封装为list返回
     */
    List<ProductsWithCategoryVo> sortWithCategory(List<Product> products);

}
