package org.gollum.bank.commandhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.ConfirmDepositCommand;
import org.gollum.bank.domain.deposit.DepositTransaction;
import org.gollum.bank.domain.deposit.DepositTransactionRepository;
import org.gollum.common.util.Assertion;
import org.gollum.core.commanding.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class ConfirmDepositCommandHandler implements CommandHandler<ConfirmDepositCommand> {

    @Autowired
    private DepositTransactionRepository repository;

    @Override
    public void exec(ConfirmDepositCommand command) {
        DepositTransaction depositTransaction = repository.getById(command.getAggregateRootId());
        Assertion.notNull(depositTransaction, "depositTransaction");
        int version = depositTransaction.getVersion();
        depositTransaction.confirmDeposit();
        repository.commit(depositTransaction, version);
    }
}
