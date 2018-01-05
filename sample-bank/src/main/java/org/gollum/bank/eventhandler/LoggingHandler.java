package org.gollum.bank.eventhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.domain.account.BankAccountCreated;
import org.gollum.bank.domain.deposit.DepositTransactionCompleted;
import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/5
 */
public abstract class LoggingHandler<T extends DomainEvent> implements EventHandler<T> {

    final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * @author wurenhai
     * @date 2018/1/4
     */
    @Component
    @Singleton
    public static class BankAccountCreatedLoggingHandler extends LoggingHandler<BankAccountCreated> {
        @Override
        public void handle(BankAccountCreated e) {
            LOG.info("id={}, version={}, owner={}", e.getAggregateRootId(), e.getVersion(), e.getOwner());
        }
    }

    /**
     * @author wurenhai
     * @date 2018/1/4
     */
    @Component
    @Singleton
    public static class DepositTransactionCompletedLoggingHandler extends LoggingHandler<DepositTransactionCompleted> {
        @Override
        public void handle(DepositTransactionCompleted e) {
            LOG.info("id={}, version={}, accountId={}", e.getAggregateRootId(), e.getVersion(), e.getAccountId());
        }
    }
}
