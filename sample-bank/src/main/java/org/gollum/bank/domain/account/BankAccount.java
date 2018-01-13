package org.gollum.bank.domain.account;

import org.gollum.bank.domain.PreparationType;
import org.gollum.bank.domain.TransactionType;
import org.gollum.core.domain.AggregateOriginator;
import org.gollum.core.domain.BaseAggregateRoot;
import org.gollum.core.eventing.AggregateSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行账户
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public class BankAccount extends BaseAggregateRoot implements AggregateOriginator {

    private String owner;

    /**
     * 账户余额
     */
    private int balance;

    /**
     * 交易预处理
     */
    private Map<String, TransactionPreparation> transactionPreparations;

    private BankAccount() {
        //required, repository create instance with default constructor
    }

    public BankAccount(String accountId, String owner) {
        super(accountId);
        applyChange(new BankAccountCreated(owner));
    }

    public void addTransactionPreparation(String transactionId,
                                          TransactionType transactionType,
                                          PreparationType preparationType,
                                          int amount) {
        int availableBalance = getAvailableBalance();
        if (preparationType == PreparationType.DebitPreparation && availableBalance < amount) {
            throw new InsufficientBalanceException(this.id, transactionId, transactionType, amount, balance, availableBalance);
        }
        applyChange(new TransactionPreparationAdded(new TransactionPreparation(this.id, transactionId, transactionType, preparationType, amount)));
    }

    public void commitTransactionPreparation(String transactionId) {
        TransactionPreparation preparation = getTransactionPreparation(transactionId);
        int currentBalance = balance;
        if (preparation.getPreparationType() == PreparationType.DebitPreparation) {
            currentBalance -= preparation.getAmount();
        } else if (preparation.getPreparationType() == PreparationType.CreditPreparation) {
            currentBalance += preparation.getAmount();
        }
        applyChange(new TransactionPreparationCommitted(currentBalance, preparation));
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    private int getAvailableBalance() {
        if (transactionPreparations.isEmpty()) {
            return balance;
        }

        int totalDebitTransactionPreparationAmount = transactionPreparations.values()
                .stream()
                .filter(a -> a.getPreparationType() == PreparationType.DebitPreparation)
                .map(a -> a.getAmount())
                .reduce(0, (a, b) -> a + b);
        return balance - totalDebitTransactionPreparationAmount;
    }

    private TransactionPreparation getTransactionPreparation(String transactionId) {
        if (!transactionPreparations.containsKey(transactionId)) {
            throw new TransactionPreparationNotExistException(this.id, transactionId);
        }
        return transactionPreparations.get(transactionId);
    }

    @Override
    public AggregateSnapshot takeSnapshot() {
        return new BankAccountSnapshot(this.id, this.version, this.owner, this.balance, this.transactionPreparations);
    }

    @Override
    public void restoreFromSnapshot(AggregateSnapshot snapshot) {
        BankAccountSnapshot snapshot1 = (BankAccountSnapshot)snapshot;
        this.id = snapshot1.getAggregateRootId();
        this.version = snapshot1.getVersion();
        this.owner = snapshot1.getOwner();
        this.balance = snapshot1.getBalance();
        this.transactionPreparations = snapshot1.getTransactionPreparations();
    }

    public void handle(BankAccountCreated e) {
        this.owner = e.getOwner();
        this.balance = 0;
        this.transactionPreparations = new HashMap<>();
    }

    public void handle(TransactionPreparationAdded e) {
        TransactionPreparation preparation = e.getTransactionPreparation();
        transactionPreparations.put(preparation.getTransactionId(), preparation);
    }

    public void handle(TransactionPreparationCommitted e) {
        transactionPreparations.remove(e.getTransactionPreparation().getTransactionId());
        balance = e.getCurrentBalance();
    }

}
