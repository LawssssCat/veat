package com.vshop.veat.mapper;

import com.vshop.veat.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vshop.veat.enums.ProductStatusEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 根据商品状态查询商品信息
     */
    List<Product> selectListByProductStatus(@Param("productStatus") ProductStatusEnum productStatus);

}
