package org.gollum.bank.commandhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.AddTransactionPreparationCommand;
import org.gollum.bank.domain.account.BankAccount;
import org.gollum.bank.domain.account.BankAccountRepository;
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
public class AddTransactionPreparationCommandHandler implements CommandHandler<AddTransactionPreparationCommand> {

    @Autowired
    private BankAccountRepository repository;

    @Override
    public void exec(AddTransactionPreparationCommand command) {
        BankAccount bankAccount = repository.getById(command.getAggregateRootId());
        Assertion.notNull(bankAccount, "BankAccount[%s]", command.getAggregateRootId());
        int version = bankAccount.getVersion();
        bankAccount.addTransactionPreparation(command.getTransactionId(),
                command.getTransactionType(),
                command.getPreparationType(),
                command.getAmount());
        repository.commit(bankAccount, version);
    }

}
