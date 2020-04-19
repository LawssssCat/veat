package com.vshop.veat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vshop.veat.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 同一个类型的产品集合
 *
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 20:18
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class ProductsWithCategoryVo {
    /**
     * 产品分类名
     */
    @JsonProperty("name")
    private String categoryName;
    /**
     * 产品分类编号
     */
    @JsonProperty("type")
    private Integer categoryType;
    /**
     * 查询出来的产品详情
     */
    @JsonProperty("foods")
    private List<ProductVo> products;

    /**
     * @param products     产品集合的数据源
     * @param categoryName 产品类型名
     * @param categoryType 产品类型标识
     * @return 一个类型的产品集合vo
     */
    public ProductsWithCategoryVo(Collection<? extends ProductVo> products, String categoryName, Integer categoryType) {
        this.setCategoryName(categoryName)
                .setCategoryType(categoryType)
                .setProducts(products instanceof List ? (List) products : new ArrayList(products));
    }

    public ProductsWithCategoryVo(String categoryName, Integer categoryType, Collection<? extends Product> products) {
        this(ProductVo.buildList(products), categoryName, categoryType);
    }
}
