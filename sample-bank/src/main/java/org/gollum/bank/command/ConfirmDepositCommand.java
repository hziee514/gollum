package org.gollum.bank.command;

import org.gollum.core.commanding.BaseCommand;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class ConfirmDepositCommand extends BaseCommand {
    public ConfirmDepositCommand(String transactionId) {
        super(transactionId);
    }
}
