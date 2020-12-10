package com.javaAdvance.hmily.stock.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaAdvance.hmily.stock.base.ResultBean;
import com.javaAdvance.hmily.stock.repository.mysql.domain.Stock;
import com.javaAdvance.hmily.stock.repository.mysql.mapper.StockMapper;
import com.javaAdvance.hmily.stock.service.StockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hitopei
 * @since 2020-12-10
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public ResultBean decrease(JSONObject data) {
        this.baseMapper.decrease(data.getInteger("goodsId"), data.getInteger("count"));
        return ResultBean.success();
    }

    public ResultBean confirm(JSONObject data){
        this.baseMapper.confirm(data.getInteger("goodsId"), data.getInteger("count"));
        return ResultBean.success();
    }

    public ResultBean cancel(JSONObject data){
        this.baseMapper.cancel(data.getInteger("goodsId"), data.getInteger("count"));
        return ResultBean.success();
    }
}
