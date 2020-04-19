package com.vshop.veat.service.impl;

import com.vshop.veat.entity.Product;
import com.vshop.veat.enums.ProductStatusEnum;
import com.vshop.veat.mapper.ProductMapper;
import com.vshop.veat.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper ;

    @Override
    public List<Product> findUpAll() {
        List<Product> products = productMapper.selectListByProductStatus(ProductStatusEnum.UP);
        return products;
    }
}
