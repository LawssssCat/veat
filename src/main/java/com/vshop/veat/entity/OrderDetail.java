package com.vshop.veat.entity;

    import java.math.BigDecimal;
    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import java.time.LocalDateTime;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 订单详情
    * </p>
*
* @author lawsssscat
* @since 2020-04-18
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ve_order_detail")
    public class OrderDetail extends Model<OrderDetail> {

    private static final long serialVersionUID = 1L;

    private String orderDetailId;

            /**
            * 订单概要id
            */
    private String orderInfoId;

            /**
            * 商品id
            */
    private String productId;

            /**
            * 商品名称
            */
    private String productName;

            /**
            * 商品单价
            */
    private BigDecimal productPrice;

            /**
            * 商品小图
            */
    private String productIcon;

            /**
            * 商品数量
            */
    private Integer productQuantity;

            /**
            * 创建时间
            */
    private LocalDateTime createTime;

            /**
            * 修改时间
            */
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.orderDetailId;
    }

}
