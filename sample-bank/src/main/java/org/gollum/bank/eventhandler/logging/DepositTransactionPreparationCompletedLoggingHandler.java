package org.gollum.bank.eventhandler.logging;

import org.gollum.bank.Singleton;
import org.gollum.bank.domain.deposit.DepositTransactionPreparationCompleted;
import org.gollum.core.eventing.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class DepositTransactionPreparationCompletedLoggingHandler implements EventHandler<DepositTransactionPreparationCompleted> {
    @Override
    public void handle(DepositTransactionPreparationCompleted event) {
        System.out.println(String.format("DepositTransactionPreparationCompleted: id=%s, version=%d, accountId=%s",
                event.getAggregateRootId(), event.getVersion(), event.getAccountId()));
    }
}
