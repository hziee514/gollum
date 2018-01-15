package org.gollum.bank.domain.transfer;

import org.gollum.core.eventing.DomainEvent;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
public class TransferTransactionStarted extends DomainEvent {

    private TransferTransactionInfo transactionInfo;

    public TransferTransactionStarted(TransferTransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public TransferTransactionInfo getTransactionInfo() {
        return transactionInfo;
    }
}
