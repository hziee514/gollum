package org.gollum.bank.domain.transfer;

import java.io.Serializable;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
public class TransferTransactionInfo implements Serializable {

    /**
     * 转出帐号ID
     */
    private String sourceAccountId;

    /**
     * 转入帐号ID
     */
    private String targetAccountId;

    /**
     * 转账金额
     */
    public int amount;

    public TransferTransactionInfo(String sourceAccountId, String targetAccountId, int amount) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public int getAmount() {
        return amount;
    }
}
