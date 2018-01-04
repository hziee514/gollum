package org.gollum.bank.commandhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.CreateAccountCommand;
import org.gollum.bank.domain.account.BankAccount;
import org.gollum.bank.domain.account.BankAccountRepository;
import org.gollum.core.commanding.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand> {

    @Autowired
    private BankAccountRepository repository;

    @Override
    public void exec(CreateAccountCommand command) {
        BankAccount bankAccount = new BankAccount(command.getAggregateRootId(), command.getOwner());
        repository.commit(bankAccount, -1);
    }
}
