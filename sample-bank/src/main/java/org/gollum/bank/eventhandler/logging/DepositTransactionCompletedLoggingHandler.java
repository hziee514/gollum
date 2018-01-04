package org.gollum.bank.eventhandler.logging;

import org.gollum.bank.Singleton;
import org.gollum.bank.domain.deposit.DepositTransactionCompleted;
import org.gollum.core.eventing.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class DepositTransactionCompletedLoggingHandler implements EventHandler<DepositTransactionCompleted> {
    @Override
    public void handle(DepositTransactionCompleted event) {
        System.out.println(String.format("DepositTransactionCompleted: id=%s, version=%d, accountId=%s",
                event.getAggregateRootId(), event.getVersion(), event.getAccountId()));
    }
}
