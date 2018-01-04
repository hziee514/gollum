package org.gollum.bank.domain.account;

import org.gollum.bank.domain.TransactionType;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class InsufficientBalanceException extends RuntimeException {

    private String accountId;

    private String transactionId;

    private TransactionType transactionType;

    private int amount;

    private int currentBalance;

    private int currentAvailableBalance;

    public InsufficientBalanceException(String accountId,
                                        String transactionId,
                                        TransactionType transactionType,
                                        int amount,
                                        int currentBalance,
                                        int currentAvailableBalance) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.currentAvailableBalance = currentAvailableBalance;
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

    public int getAmount() {
        return amount;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public int getCurrentAvailableBalance() {
        return currentAvailableBalance;
    }
}
