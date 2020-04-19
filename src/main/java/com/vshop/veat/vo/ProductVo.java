package com.vshop.veat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vshop.veat.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 商品详情
 *
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 20:24
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class ProductVo {
    @JsonProperty("id")
    private String productId;
    /**
     * 产品名称
     */
    @JsonProperty("name")
    private String productName;
    /**
     * 产品价格
     */
    @JsonProperty("price")
    private BigDecimal productPrice;
    /**
     * 产品介绍
     */
    @JsonProperty("description")
    private String productDescription;
    /**
     * 产品图标
     */
    @JsonProperty("icon")
    private String productIcon;

    /**
     * po集合封装成vo集合
     */
    public static List<ProductVo> buildList(Collection<? extends Product> products) {
        List<ProductVo> list = new ArrayList<>();
        for (Product product : products) {
            list.add(new ProductVo(product));
        }
        return list;
    }

    /**
     * po对象封装成vo
     */
    public ProductVo(Product product) {
        this.setProductId(product.getProductId())
                .setProductDescription(product.getProductDescription())
                .setProductIcon(product.getProductIcon())
                .setProductName(product.getProductName())
                .setProductPrice(product.getProductPrice());
    }

}
