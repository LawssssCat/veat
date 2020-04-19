package com.vshop.veat.vo;

import lombok.Data;
import lombok.Getter;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/18 20:07
 */
@Getter
public class JsonResult<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回的具体内容
     */
    private T data;

    private JsonResult() {
    }

    public static JsonResult ok() {
        return new JsonResult().code(0).msg("成功");
    }

    public JsonResult code(Integer code) {
        this.code = code;
        return this;
    }

    public JsonResult msg(String msg) {
        this.msg = msg;
        return this;
    }

    public JsonResult<T> data(T data) {
        this.data = data;
        return this;
    }
}
