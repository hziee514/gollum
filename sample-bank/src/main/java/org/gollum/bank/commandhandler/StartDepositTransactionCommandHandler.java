package org.gollum.bank.commandhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.StartDepositTransactionCommand;
import org.gollum.bank.domain.deposit.DepositTransaction;
import org.gollum.bank.domain.deposit.DepositTransactionRepository;
import org.gollum.core.commanding.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class StartDepositTransactionCommandHandler implements CommandHandler<StartDepositTransactionCommand> {

    @Autowired
    private DepositTransactionRepository repository;

    @Override
    public void exec(StartDepositTransactionCommand command) {
        DepositTransaction transaction = new DepositTransaction(command.getAggregateRootId(), command.getAccountId(), command.getAmount());
        repository.commit(transaction, -1);
    }

}
