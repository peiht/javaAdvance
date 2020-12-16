package com.javaAdvance.account.api.service.impl;

import com.javaAdvance.account.api.repository.mysql.mapper.AccountFreezeMapper;
import com.javaAdvance.account.api.repository.mysql.mapper.AccountRmbMapper;
import com.javaAdvance.account.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author hitopei
 * account service impl
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRmbMapper accountRmbMapper;
    @Autowired
    private AccountFreezeMapper accountFreezeMapper;

    @Override
    public Boolean transferRMB(BigDecimal amount, String fromUserId, String toUserId) {
        //减去当前余额RMB
        int updateRes = accountRmbMapper.update(fromUserId, amount);
        //增加冻结表的RMB
        int freezeUpdate = accountFreezeMapper.updateFreezeRMB(fromUserId, amount);
        //dubbo 发送请求到b
        BigDecimal usd = amount.divide(BigDecimal.valueOf(7), RoundingMode.HALF_DOWN);
        return null;
    }

    public Boolean confirmRMB(BigDecimal amount, String userId) {
        //解冻RMB
        int unFreeze = accountFreezeMapper.unFreezeRMB(userId, amount);
        return Boolean.TRUE;
    }

    public Boolean cancelRMB(BigDecimal amount, String userId) {
        //RMB回归
        int accountRes = accountRmbMapper.cancal(userId, amount);
        //解冻RMB
        int unFreeze = accountFreezeMapper.unFreezeRMB(userId, amount);
        return Boolean.TRUE;
    }

    @Override
    public Boolean transferUSD(BigDecimal amount) {
        return null;
    }


}
