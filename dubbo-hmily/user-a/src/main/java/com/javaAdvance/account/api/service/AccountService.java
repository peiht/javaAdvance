package com.javaAdvance.account.api.service;

import java.math.BigDecimal;

/**
 *
 * @author hitopeis
 * account interface
 */
public interface AccountService {

    Boolean consumeUsd(BigDecimal amount, String fromUserId, String toUserId);
}
