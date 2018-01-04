package org.gollum.bank.saga.deposit;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.ConfirmDepositCommand;
import org.gollum.bank.domain.PreparationType;
import org.gollum.bank.domain.TransactionType;
import org.gollum.bank.domain.account.TransactionPreparation;
import org.gollum.bank.domain.account.TransactionPreparationCommitted;
import org.gollum.core.commanding.CommandBus;
import org.gollum.core.eventing.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class TransactionPreparationCommittedSagaHandler implements EventHandler<TransactionPreparationCommitted> {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void handle(TransactionPreparationCommitted event) {
        TransactionPreparation preparation = event.getTransactionPreparation();
        if (preparation.getTransactionType() == TransactionType.DepositTransaction
                && preparation.getPreparationType() == PreparationType.CreditPreparation) {
            ctx.getBean(CommandBus.class).send(new ConfirmDepositCommand(preparation.getTransactionId()));
        }
    }
}
