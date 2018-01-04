package org.gollum.bank.domain.deposit;

import org.gollum.bank.domain.TransactionStatus;
import org.gollum.core.eventing.BaseAggregateSnapshot;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class DepositTransactionSnapshot extends BaseAggregateSnapshot {

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

    public DepositTransactionSnapshot(String aggregateRootId, int version,
                                      String accountId, int amount, TransactionStatus status) {
        super(aggregateRootId, version);
        this.accountId = accountId;
        this.amount = amount;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }
}
