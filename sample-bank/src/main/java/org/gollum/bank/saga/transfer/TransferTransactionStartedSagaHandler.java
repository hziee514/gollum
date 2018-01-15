package org.gollum.bank.saga.transfer;

import org.gollum.bank.Singleton;
import org.gollum.bank.command.ValidateAccountCommand;
import org.gollum.bank.domain.transfer.TransferTransactionInfo;
import org.gollum.bank.domain.transfer.TransferTransactionStarted;
import org.gollum.core.commanding.CommandBus;
import org.gollum.core.eventing.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
@Component
@Singleton
public class TransferTransactionStartedSagaHandler implements EventHandler<TransferTransactionStarted> {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void handle(TransferTransactionStarted event) {
        CommandBus bus = ctx.getBean(CommandBus.class);
        TransferTransactionInfo transactionInfo = event.getTransactionInfo();
        bus.send(new ValidateAccountCommand(transactionInfo.getSourceAccountId(), event.getAggregateRootId()));
        bus.send(new ValidateAccountCommand(transactionInfo.getSourceAccountId(), event.getAggregateRootId()));
    }

}
