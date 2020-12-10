package com.javaAdvance.hmily.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.javaAdvance.hmily.user.base.ResultBean;
import com.javaAdvance.hmily.user.repository.mysql.domain.User;
import com.javaAdvance.hmily.user.repository.mysql.mapper.UserMapper;
import com.javaAdvance.hmily.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hitopei
 * @since 2020-12-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public ResultBean payAccount(JSONObject data) {
        Integer userId = data.getInteger("userId");
        BigDecimal amount = data.getBigDecimal("amount");
        Integer count = data.getInteger("count");
        BigDecimal amountAll = BigDecimal.valueOf(count).add(amount);
        this.baseMapper.updateAccount(userId, amountAll);
        return ResultBean.success();
    }


    public int confirm(JSONObject data){
        Integer userId = data.getInteger("userId");
        BigDecimal amount = data.getBigDecimal("amount");
        Integer count = data.getInteger("count");
        BigDecimal amountAll = BigDecimal.valueOf(count).add(amount);
        return this.baseMapper.confirm(userId, amountAll);
    }

    public int cancel(JSONObject data){
        Integer userId = data.getInteger("userId");
        BigDecimal amount = data.getBigDecimal("amount");
        Integer count = data.getInteger("count");
        BigDecimal amountAll = BigDecimal.valueOf(count).add(amount);
        return this.baseMapper.cancel(userId, amountAll);
    }
}
