package com.vshop.veat.service.impl;

import com.vshop.veat.entity.OrderInfo;
import com.vshop.veat.mapper.OrderInfoMapper;
import com.vshop.veat.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单概要(主表) 服务实现类
 * </p>
 *
 * @author lawsssscat
 * @since 2020-04-18
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
