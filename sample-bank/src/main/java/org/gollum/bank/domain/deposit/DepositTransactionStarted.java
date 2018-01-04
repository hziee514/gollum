package org.gollum.bank.domain.deposit;

import org.gollum.core.eventing.DomainEvent;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class DepositTransactionStarted extends DomainEvent {

    private String accountId;

    private int amount;

    public DepositTransactionStarted(String accountId, int amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }
}
