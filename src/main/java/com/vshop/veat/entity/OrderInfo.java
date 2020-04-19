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
    * 订单概要(主表)
    * </p>
*
* @author lawsssscat
* @since 2020-04-18
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ve_order_info")
    public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

    private String orderInfoId;

            /**
            * 买家名字
            */
    private String buyerName;

            /**
            * 买家电话
            */
    private String buyerPhone;

            /**
            * 买家地址
            */
    private String buyerAddress;

            /**
            * 买家微信openid
            */
    private String buyerOpenid;

            /**
            * 订单总金额
            */
    private BigDecimal orderAmount;

            /**
            * 订单状态，默认0新下单
            */
    private Integer orderStatus;

            /**
            * 支付状态，默认0未支付
            */
    private Integer paySataus;

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
        return this.orderInfoId;
    }

}
