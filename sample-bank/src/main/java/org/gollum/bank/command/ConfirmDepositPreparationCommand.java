package org.gollum.bank.command;

import org.gollum.core.commanding.BaseCommand;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class ConfirmDepositPreparationCommand extends BaseCommand {

    public ConfirmDepositPreparationCommand(String transactionId) {
        super(transactionId);
    }

}
