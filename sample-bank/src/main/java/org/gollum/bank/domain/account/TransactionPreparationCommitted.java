package org.gollum.bank.domain.account;

import org.gollum.core.eventing.DomainEvent;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class TransactionPreparationCommitted extends DomainEvent {

    private int currentBalance;

    private TransactionPreparation transactionPreparation;

    public TransactionPreparationCommitted(int currentBalance, TransactionPreparation transactionPreparation) {
        this.currentBalance = currentBalance;
        this.transactionPreparation = transactionPreparation;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public TransactionPreparation getTransactionPreparation() {
        return transactionPreparation;
    }
}
