package org.gollum.bank.domain.transfer;

import org.gollum.bank.domain.TransactionStatus;
import org.gollum.core.domain.AggregateOriginator;
import org.gollum.core.domain.BaseAggregateRoot;
import org.gollum.core.eventing.AggregateSnapshot;

/**
 * 转账交易
 *
 * @author wurenhai
 * @date 2018/1/15
 */
public class TransferTransaction extends BaseAggregateRoot implements AggregateOriginator {

    /**
     * 交易信息
     */
    private TransferTransactionInfo transactionInfo;

    /**
     * 交易状态
     */
    private TransactionStatus status;

    private boolean sourceAccountValidated;
    private boolean targetAccountValidated;
    private boolean transferOutPrepared;
    private boolean transferInPrepared;
    private boolean transferOutConfirmed;
    private boolean transferInConfirmed;

    private TransferTransaction() {
        //必须有默认构造函数用于仓库构建聚合根实例
    }

    public TransferTransaction(String transactionId, TransferTransactionInfo transactionInfo) {
        super(transactionId);
        applyChange(new TransferTransactionStarted(transactionInfo));
    }

    @Override
    public AggregateSnapshot takeSnapshot() {
        return new TransferTransactionSnapshot(this.id, this.version,
                this.transactionInfo, this.status,
                this.sourceAccountValidated, this.targetAccountValidated,
                this.transferOutPrepared, this.transferInPrepared,
                this.transferOutConfirmed, this.transferInConfirmed);
    }

    @Override
    public void restoreFromSnapshot(AggregateSnapshot snapshot) {
        TransferTransactionSnapshot s = (TransferTransactionSnapshot)snapshot;
        this.id = s.getAggregateRootId();
        this.version = s.getVersion();
        this.transactionInfo = s.getTransactionInfo();
        this.status = s.getStatus();
        this.sourceAccountValidated = s.isSourceAccountValidated();
        this.targetAccountValidated = s.isTargetAccountValidated();
        this.transferOutPrepared = s.isTransferOutPrepared();
        this.transferInPrepared = s.isTransferInPrepared();
        this.transferOutConfirmed = s.isTransferOutConfirmed();
        this.transferInConfirmed = s.isTransferInConfirmed();
    }

    private void handle(TransferTransactionStarted e) {
        this.transactionInfo = e.getTransactionInfo();
        this.status = TransactionStatus.Started;
    }

}
