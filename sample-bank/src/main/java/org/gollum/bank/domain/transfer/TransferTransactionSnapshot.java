package org.gollum.bank.domain.transfer;

import org.gollum.bank.domain.TransactionStatus;
import org.gollum.core.eventing.BaseAggregateSnapshot;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
public class TransferTransactionSnapshot extends BaseAggregateSnapshot {

    private TransferTransactionInfo transactionInfo;

    private TransactionStatus status;

    private boolean sourceAccountValidated;
    private boolean targetAccountValidated;
    private boolean transferOutPrepared;
    private boolean transferInPrepared;
    private boolean transferOutConfirmed;
    private boolean transferInConfirmed;

    public TransferTransactionSnapshot(String aggregateRootId, int version,
                                       TransferTransactionInfo transactionInfo, TransactionStatus status,
                                       boolean sourceAccountValidated, boolean targetAccountValidated,
                                       boolean transferOutPrepared, boolean transferInPrepared,
                                       boolean transferOutConfirmed, boolean transferInConfirmed) {
        super(aggregateRootId, version);
        this.transactionInfo = transactionInfo;
        this.status = status;
        this.sourceAccountValidated = sourceAccountValidated;
        this.targetAccountValidated = targetAccountValidated;
        this.transferOutPrepared = transferOutPrepared;
        this.transferInPrepared = transferInPrepared;
        this.transferOutConfirmed = transferOutConfirmed;
        this.transferInConfirmed = transferInConfirmed;
    }

    public TransferTransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public boolean isSourceAccountValidated() {
        return sourceAccountValidated;
    }

    public boolean isTargetAccountValidated() {
        return targetAccountValidated;
    }

    public boolean isTransferOutPrepared() {
        return transferOutPrepared;
    }

    public boolean isTransferInPrepared() {
        return transferInPrepared;
    }

    public boolean isTransferOutConfirmed() {
        return transferOutConfirmed;
    }

    public boolean isTransferInConfirmed() {
        return transferInConfirmed;
    }
}
