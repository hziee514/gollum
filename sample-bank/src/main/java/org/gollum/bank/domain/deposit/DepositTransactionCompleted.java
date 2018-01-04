package org.gollum.bank.domain.deposit;

import org.gollum.core.eventing.DomainEvent;

/**
 * 存款交易完成
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public class DepositTransactionCompleted extends DomainEvent {

    private String accountId;

    public DepositTransactionCompleted(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}
