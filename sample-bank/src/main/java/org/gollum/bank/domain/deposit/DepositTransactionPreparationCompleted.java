package org.gollum.bank.domain.deposit;

import org.gollum.core.eventing.DomainEvent;

/**
 * 预存款已确认
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public class DepositTransactionPreparationCompleted extends DomainEvent {

    private String accountId;

    public DepositTransactionPreparationCompleted(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}
