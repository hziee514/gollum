package org.gollum.bank.command;

import org.gollum.bank.domain.transfer.TransferTransactionInfo;
import org.gollum.core.commanding.BaseCommand;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
public class StartTransferTransactionCommand extends BaseCommand {

    private TransferTransactionInfo transactionInfo;

    public StartTransferTransactionCommand(String transactionId, TransferTransactionInfo transactionInfo) {
        super(transactionId);
        this.transactionInfo = transactionInfo;
    }

    public TransferTransactionInfo getTransactionInfo() {
        return transactionInfo;
    }
}
