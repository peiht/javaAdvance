package com.javaAdvance.account.api.service;

import java.math.BigDecimal;

/**
 *
 * @author hitopeis
 * account interface
 */
public interface AccountService {

    /**
     * transfer amount
     * @param amount the coin
     * @return Boolean
     */
    Boolean transfer(BigDecimal amount);

    Boolean consumeUsd(BigDecimal amount, String fromUserId, String toUserId);
}
