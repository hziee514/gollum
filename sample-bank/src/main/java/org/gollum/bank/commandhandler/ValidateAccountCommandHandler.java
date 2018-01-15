package org.gollum.bank.commandhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.ValidateAccountCommand;
import org.gollum.bank.domain.account.BankAccount;
import org.gollum.bank.domain.account.BankAccountRepository;
import org.gollum.core.commanding.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
@Component
@Singleton
public class ValidateAccountCommandHandler implements CommandHandler<ValidateAccountCommand> {

    @Autowired
    private BankAccountRepository repository;

    @Override
    public void exec(ValidateAccountCommand command) {
        BankAccount bankAccount = repository.getById(command.getAggregateRootId());
        if (bankAccount == null) {
            //failed
        } else {

        }
    }

}
