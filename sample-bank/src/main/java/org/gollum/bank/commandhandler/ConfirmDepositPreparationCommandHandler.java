package org.gollum.bank.commandhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.ConfirmDepositPreparationCommand;
import org.gollum.bank.domain.deposit.DepositTransaction;
import org.gollum.bank.domain.deposit.DepositTransactionRepository;
import org.gollum.core.commanding.CommandHandler;
import org.gollum.core.common.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class ConfirmDepositPreparationCommandHandler implements CommandHandler<ConfirmDepositPreparationCommand> {

    @Autowired
    private DepositTransactionRepository repository;

    @Override
    public void exec(ConfirmDepositPreparationCommand command) {
        DepositTransaction depositTransaction = repository.getById(command.getAggregateRootId(), DepositTransaction.class);
        Assertion.notNull(depositTransaction, "depositTransaction");
        int version = depositTransaction.getVersion();
        depositTransaction.confirmDepositPreparation();
        repository.commit(depositTransaction, version);
    }

}
