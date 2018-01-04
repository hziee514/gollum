package org.gollum.bank.commandhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.CommitTransactionPreparationCommand;
import org.gollum.bank.domain.account.BankAccount;
import org.gollum.bank.domain.account.BankAccountRepository;
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
public class CommitTransactionPreparationCommandHandler implements CommandHandler<CommitTransactionPreparationCommand> {

    @Autowired
    private BankAccountRepository repository;

    @Override
    public void exec(CommitTransactionPreparationCommand command) {
        BankAccount bankAccount = repository.getById(command.getAggregateRootId(), BankAccount.class);
        Assertion.notNull(bankAccount, "bankAccount");
        int version = bankAccount.getVersion();
        bankAccount.commitTransactionPreparation(command.getTransactionId());
        repository.commit(bankAccount, version);
    }

}
