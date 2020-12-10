package com.javaAdvance.hmily.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.javaAdvance.hmily.order.base.ResultBean;
import com.javaAdvance.hmily.order.client.StockFeign;
import com.javaAdvance.hmily.order.client.UserFeign;
import com.javaAdvance.hmily.order.repository.mysql.domain.GoodsOrder;
import com.javaAdvance.hmily.order.repository.mysql.mapper.GoodsOrderMapper;
import com.javaAdvance.hmily.order.service.GoodsOrderService;
import com.javaAdvance.hmily.order.service.PayService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author hitopei
 *
 * 支付
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private GoodsOrderMapper orderMapper;
    @Autowired
    private StockFeign stockFeign;
    @Autowired
    private UserFeign userFeign;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancels")
    public ResultBean pay(Integer userId, Integer count, BigDecimal amount, Integer goodsId) {
        ResultBean<GoodsOrder> resultBean = goodsOrderService.createOrder(userId, count, amount, goodsId);
        GoodsOrder order = resultBean.getData();
        //修改订单状态
        updateProcess(order);
        //扣减用户表的余额
        JSONObject accountData = new JSONObject();
        accountData.put("userId", userId);
        accountData.put("amount", amount);
        accountData.put("count", count);
        userFeign.accountPay(accountData);
        //扣减库存表库存
        JSONObject stockData = new JSONObject();
        stockData.put("goodsId", goodsId);
        stockData.put("count", count);
        stockFeign.decreaseStock(stockData);
        return ResultBean.success();
    }

    public void updateProcess(GoodsOrder order){
        orderMapper.update(order.getOrderId(), 1);
    }

    public void confirm(GoodsOrder order) {
        orderMapper.update(order.getOrderId(), 2);
    }

    public void cancel(GoodsOrder order) {
        orderMapper.update(order.getOrderId(), 3);
    }
}
