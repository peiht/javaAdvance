package com.javaAdvance.hmily.order.service.impl;

import com.javaAdvance.hmily.order.repository.mysql.domain.GoodsOrder;
import com.javaAdvance.hmily.order.repository.mysql.mapper.GoodsOrderMapper;
import com.javaAdvance.hmily.order.service.GoodsOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hitopei
 * @since 2020-12-09
 */
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {

}
