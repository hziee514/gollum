package org.gollum.bank.command;

import org.gollum.core.commanding.BaseCommand;

/**
 * 发起存款交易
 *
 * @author wurenhai
 * @date 2018/1/4
 */
public class StartDepositTransactionCommand extends BaseCommand {

    private String accountId;

    private int amount;

    public StartDepositTransactionCommand(String transactionId, String accountId, int amount) {
        super(transactionId);
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }
}
