package org.gollum.bank.eventhandler.logging;

import org.gollum.bank.Singleton;
import org.gollum.bank.domain.deposit.DepositTransactionStarted;
import org.gollum.core.eventing.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class DepositTransactionStartedLoggingHandler implements EventHandler<DepositTransactionStarted> {
    @Override
    public void handle(DepositTransactionStarted event) {
        System.out.println(String.format("DepositTransactionStarted: id=%s, version=%d, accountId=%s, amount=%d",
                event.getAggregateRootId(), event.getVersion(), event.getAccountId(), event.getAmount()));
    }
}
