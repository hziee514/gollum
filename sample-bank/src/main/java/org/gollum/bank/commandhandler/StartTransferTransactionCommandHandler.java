package org.gollum.bank.commandhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.StartTransferTransactionCommand;
import org.gollum.bank.domain.transfer.TransferTransaction;
import org.gollum.bank.domain.transfer.TransferTransactionRepository;
import org.gollum.core.commanding.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
@Component
@Singleton
public class StartTransferTransactionCommandHandler implements CommandHandler<StartTransferTransactionCommand> {

    @Autowired
    private TransferTransactionRepository repository;

    @Override
    public void exec(StartTransferTransactionCommand command) {
        repository.commit(new TransferTransaction(command.getAggregateRootId(), command.getTransactionInfo()));
    }

}
