package org.gollum.bank.saga.deposit;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.CommitTransactionPreparationCommand;
import org.gollum.bank.domain.deposit.DepositTransactionPreparationCompleted;
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
public class DepositTransactionPreparationCompletedSagaHandler implements EventHandler<DepositTransactionPreparationCompleted> {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void handle(DepositTransactionPreparationCompleted event) {
        ctx.getBean(CommandBus.class)
                .send(new CommitTransactionPreparationCommand(event.getAccountId(), event.getAggregateRootId()));
    }

}
