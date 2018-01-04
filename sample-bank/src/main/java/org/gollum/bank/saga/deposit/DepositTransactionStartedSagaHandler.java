package org.gollum.bank.saga.deposit;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.AddTransactionPreparationCommand;
import org.gollum.bank.domain.PreparationType;
import org.gollum.bank.domain.TransactionType;
import org.gollum.bank.domain.deposit.DepositTransactionStarted;
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
public class DepositTransactionStartedSagaHandler implements EventHandler<DepositTransactionStarted> {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void handle(DepositTransactionStarted event) {
        ctx.getBean(CommandBus.class)
                .send(new AddTransactionPreparationCommand(event.getAccountId(),
                        event.getAggregateRootId(),
                        TransactionType.DepositTransaction,
                        PreparationType.CreditPreparation,
                        event.getAmount()));
    }
}
