package org.gollum.bank.command;

import org.gollum.core.commanding.BaseCommand;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
public class ValidateAccountCommand extends BaseCommand {

    private String transactionId;

    public ValidateAccountCommand(String accountId, String transactionId) {
        super(accountId);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
