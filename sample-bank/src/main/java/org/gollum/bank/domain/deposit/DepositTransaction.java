package org.gollum.bank.domain.deposit;

import org.gollum.bank.domain.TransactionStatus;
import org.gollum.core.domain.AggregateOriginator;
import org.gollum.core.domain.BaseAggregateRoot;
import org.gollum.core.eventing.AggregateSnapshot;

/**
 * 存款交易
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public class DepositTransaction extends BaseAggregateRoot implements AggregateOriginator {

    /**
     * 存款账户ID
     */
    private String accountId;

    /**
     * 存款金额
     */
    private int amount;

    /**
     * 交易状态
     */
    private TransactionStatus status;

    private DepositTransaction() {
        //required, repository create instance with default constructor
    }

    public DepositTransaction(String id, String accountId, int amount) {
        super(id);
        applyChange(new DepositTransactionStarted(accountId, amount));
    }

    /**
     * 确认预存款
     */
    public void confirmDepositPreparation() {
        if (status == TransactionStatus.Started) {
            applyChange(new DepositTransactionPreparationCompleted(this.accountId));
        }
    }

    public void confirmDeposit() {
        if (status == TransactionStatus.PreparationCompleted) {
            applyChange(new DepositTransactionCompleted(this.accountId));
        }
    }

    @Override
    public AggregateSnapshot takeSnapshot() {
        return new DepositTransactionSnapshot(this.id, this.version, this.accountId, this.amount, this.status);
    }

    @Override
    public void restoreFromSnapshot(AggregateSnapshot snapshot) {
        DepositTransactionSnapshot snapshot1 = (DepositTransactionSnapshot)snapshot;
        this.id = snapshot1.getAggregateRootId();
        this.version = snapshot1.getVersion();
        this.accountId = snapshot1.getAccountId();
        this.amount = snapshot1.getAmount();
        this.status = snapshot1.getStatus();
    }

    public void handle(DepositTransactionStarted e) {
        this.accountId = e.getAccountId();
        this.amount = e.getAmount();
        this.status = TransactionStatus.Started;
    }

    public void handle(DepositTransactionPreparationCompleted e) {
        this.status = TransactionStatus.PreparationCompleted;
    }

    public void handle(DepositTransactionCompleted e) {
        status = TransactionStatus.Cancelled;
    }

}
