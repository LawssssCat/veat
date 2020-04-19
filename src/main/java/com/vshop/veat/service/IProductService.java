package com.vshop.veat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vshop.veat.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
public interface IProductService extends IService<Product> {

    /**
     * @return 查询所有在架商品列表
     */
    List<Product> findUpAll();

    // TODO 加库存
    // TODO 减库存
}
