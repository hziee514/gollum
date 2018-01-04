package org.gollum.bank.domain.account;

import org.gollum.core.eventing.BaseAggregateSnapshot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class BankAccountSnapshot extends BaseAggregateSnapshot {

    private String owner;

    private int balance;

    private Map<String, TransactionPreparation> transactionPreparations;

    public BankAccountSnapshot(String aggregateRootId, int version,
                               String owner, int balance,
                               Map<String, TransactionPreparation> preparations) {
        super(aggregateRootId, version);
        this.owner = owner;
        this.balance = balance;
        this.transactionPreparations = new HashMap<>(preparations);
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public Map<String, TransactionPreparation> getTransactionPreparations() {
        return new HashMap<>(transactionPreparations);
    }
}
