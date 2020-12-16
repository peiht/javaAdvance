package com.javaAdvance.account.api.service.impl;

import com.javaAdvance.account.api.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author hitopei
 * account service impl
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Boolean transfer(BigDecimal amount) {
        return null;
    }

    public Boolean confirm(BigDecimal amount) {
        return Boolean.TRUE;
    }

    public Boolean cancel(BigDecimal amount) {
        return Boolean.TRUE;
    }
}
