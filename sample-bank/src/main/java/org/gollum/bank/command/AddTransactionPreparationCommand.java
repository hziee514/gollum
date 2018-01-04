package org.gollum.bank.command;

import org.gollum.bank.domain.PreparationType;
import org.gollum.bank.domain.TransactionType;
import org.gollum.core.commanding.BaseCommand;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class AddTransactionPreparationCommand extends BaseCommand {

    /**
     * 交易ID
     */
    private String transactionId;

    /**
     * 交易类型
     */
    private TransactionType transactionType;

    /**
     * 预付类型
     */
    private PreparationType preparationType;

    /**
     * 金额
     */
    private int amount;

    public AddTransactionPreparationCommand(String accountId,
                                            String transactionId,
                                            TransactionType transactionType,
                                            PreparationType preparationType,
                                            int amount) {
        super(accountId);
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.preparationType = preparationType;
        this.amount = amount;
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
