package com.vshop.veat.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * ve_product_category
 * @author 
 */
@Data
public class ProductCategory implements Serializable {
    private Integer productCategoryId;

    /**
     * 类目名字
     */
    private String productCategoryName;

    /**
     * 类目编号
     */
    private Integer productCategoryType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}