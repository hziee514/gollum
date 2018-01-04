package org.gollum.bank.eventhandler.logging;

import org.gollum.bank.Singleton;
import org.gollum.bank.domain.account.TransactionPreparation;
import org.gollum.bank.domain.account.TransactionPreparationAdded;
import org.gollum.core.eventing.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Component
@Singleton
public class TransactionPreparationAddedLoggingHandler implements EventHandler<TransactionPreparationAdded> {
    @Override
    public void handle(TransactionPreparationAdded event) {
        TransactionPreparation preparation = event.getTransactionPreparation();
        System.out.println(String.format("TransactionPreparationAdded: id=%s, version=%d, " +
                        "preparation(accountId=%s, transactionId=%s, transactionType=%s, preparationType=%s, amount=%d)",
                event.getAggregateRootId(), event.getVersion(),
                preparation.getAccountId(), preparation.getTransactionId(),
                preparation.getTransactionType().toString(), preparation.getPreparationType().toString(), preparation.getAmount()));
    }
}
