package com.vshop.veat.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * 商品状态
 *
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 17:45
 */
public enum ProductStatusEnum implements IEnum<Integer> {
    UP(0, "上架"),
    DOWN(1, "下架");

    private Integer status;
    private String msg;

    ProductStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public Integer getValue() {
        return status;
    }

    @Override
    public String toString() {
        return msg;
    }
}
