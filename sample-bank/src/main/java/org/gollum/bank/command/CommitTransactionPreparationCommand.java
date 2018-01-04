package org.gollum.bank.command;

import org.gollum.core.commanding.BaseCommand;

/**
 * 提交预操作
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public class CommitTransactionPreparationCommand extends BaseCommand {

    private String transactionId;

    public CommitTransactionPreparationCommand(String accountId, String transactionId) {
        super(accountId);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

}
