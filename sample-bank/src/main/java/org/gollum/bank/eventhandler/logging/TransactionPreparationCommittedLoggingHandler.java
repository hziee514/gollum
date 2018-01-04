package org.gollum.bank.eventhandler.logging;

import org.gollum.bank.Singleton;
import org.gollum.bank.domain.account.TransactionPreparation;
import org.gollum.bank.domain.account.TransactionPreparationCommitted;
import org.gollum.core.eventing.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class TransactionPreparationCommittedLoggingHandler implements EventHandler<TransactionPreparationCommitted> {
    @Override
    public void handle(TransactionPreparationCommitted event) {
        TransactionPreparation preparation = event.getTransactionPreparation();
        System.out.println(String.format("TransactionPreparationCommitted: id=%s, version=%d, currentBalance=%d, " +
                        "preparation(accountId=%s, transactionId=%s, transactionType=%s, preparationType=%s, amount=%d)",
                event.getAggregateRootId(), event.getVersion(), event.getCurrentBalance(),
                preparation.getAccountId(), preparation.getTransactionId(),
                preparation.getTransactionType().toString(), preparation.getPreparationType().toString(), preparation.getAmount()));
    }
}
