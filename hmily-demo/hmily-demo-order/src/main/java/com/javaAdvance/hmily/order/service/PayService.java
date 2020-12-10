package com.javaAdvance.hmily.order.service;

import com.javaAdvance.hmily.order.base.ResultBean;

import java.math.BigDecimal;

public interface PayService {

    ResultBean pay(Integer userId, Integer count, BigDecimal amount, Integer goodsId);
}
