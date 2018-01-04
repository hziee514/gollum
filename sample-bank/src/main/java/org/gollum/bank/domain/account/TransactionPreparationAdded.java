package org.gollum.bank.domain.account;

import org.gollum.core.eventing.DomainEvent;

/**
 *
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public class TransactionPreparationAdded extends DomainEvent {

    private TransactionPreparation transactionPreparation;

    public TransactionPreparationAdded(TransactionPreparation transactionPreparation) {
        this.transactionPreparation = transactionPreparation;
    }

    public TransactionPreparation getTransactionPreparation() {
        return transactionPreparation;
    }
}
