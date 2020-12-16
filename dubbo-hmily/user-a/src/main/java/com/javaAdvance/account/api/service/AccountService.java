package com.javaAdvance.account.api.service;

import java.math.BigDecimal;

/**
 *
 * @author hitopeis
 * account interface
 */
public interface AccountService {

    /**
     * transfer from rmb to usd
     * @param amount the coin
     * @param fromUserId  source user id
     * @param toUserId target user id
     * @return Boolean
     */
    Boolean transferRMB(BigDecimal amount, String fromUserId, String toUserId);

    /**
     * transfer from usd to rmb
     * @param amount
     * @return
     */
    Boolean transferUSD(BigDecimal amount);
}
