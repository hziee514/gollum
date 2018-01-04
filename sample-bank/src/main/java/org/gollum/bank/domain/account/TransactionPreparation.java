package org.gollum.bank.domain.account;

import org.gollum.bank.domain.PreparationType;
import org.gollum.bank.domain.TransactionType;

import java.io.Serializable;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class TransactionPreparation implements Serializable {

    private String accountId;

    private String transactionId;

    private TransactionType transactionType;

    private PreparationType preparationType;

    private int amount;

    public TransactionPreparation(String accountId,
                                  String transactionId,
                                  TransactionType transactionType,
                                  PreparationType preparationType,
                                  int amount) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.preparationType = preparationType;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public PreparationType getPreparationType() {
        return preparationType;
    }

    public int getAmount() {
        return amount;
    }
}
