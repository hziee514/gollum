package org.gollum.bank.eventhandler;

import org.gollum.bank.Singleton;
import org.gollum.bank.domain.account.BankAccountCreated;
import org.gollum.bank.domain.account.TransactionPreparation;
import org.gollum.bank.domain.account.TransactionPreparationAdded;
import org.gollum.bank.domain.account.TransactionPreparationCommitted;
import org.gollum.bank.domain.deposit.DepositTransactionCompleted;
import org.gollum.bank.domain.deposit.DepositTransactionPreparationCompleted;
import org.gollum.bank.domain.deposit.DepositTransactionStarted;
import org.gollum.bank.domain.transfer.TransferTransaction;
import org.gollum.bank.domain.transfer.TransferTransactionInfo;
import org.gollum.bank.domain.transfer.TransferTransactionStarted;
import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/5
 */
public abstract class LogHandler<T extends DomainEvent> implements EventHandler<T> {

    final Logger LOG = LoggerFactory.getLogger(getClass());

    /**
     * @author wurenhai
     * @date 2018/1/4
     */
    @Component
    @Singleton
    public static class BankAccountCreatedLogHandler extends LogHandler<BankAccountCreated> {
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
    public static class DepositTransactionCompletedLogHandler extends LogHandler<DepositTransactionCompleted> {
        @Override
        public void handle(DepositTransactionCompleted e) {
            LOG.info("id={}, version={}, accountId={}", e.getAggregateRootId(), e.getVersion(), e.getAccountId());
        }
    }

    /**
     * @author wurenhai
     * @date 2018/1/4
     */
    @Component
    @Singleton
    public static class DepositTransactionPreparationCompletedLogHandler extends LogHandler<DepositTransactionPreparationCompleted> {
        @Override
        public void handle(DepositTransactionPreparationCompleted e) {
            LOG.info("id={}, version={}, accountId={}", e.getAggregateRootId(), e.getVersion(), e.getAccountId());
        }
    }

    /**
     * @author wurenhai
     * @date 2018/1/4
     */
    @Component
    @Singleton
    public static class DepositTransactionStartedLogHandler extends LogHandler<DepositTransactionStarted> {
        @Override
        public void handle(DepositTransactionStarted e) {
            LOG.info("id={}, version={}, accountId={}, amount={}",
                    e.getAggregateRootId(), e.getVersion(), e.getAccountId(), e.getAmount());
        }
    }

    /**
     * @author wurenhai
     * @date 2018/1/4
     */
    @Component
    @Singleton
    public static class TransactionPreparationAddedLogHandler extends LogHandler<TransactionPreparationAdded> {
        @Override
        public void handle(TransactionPreparationAdded e) {
            TransactionPreparation preparation = e.getTransactionPreparation();
            LOG.info("id={}, version={}, preparation(accountId={}, transactionId={}, transactionType={}, preparationType={}, amount={})",
                    e.getAggregateRootId(), e.getVersion(),
                    preparation.getAccountId(), preparation.getTransactionId(),
                    preparation.getTransactionType(), preparation.getPreparationType(), preparation.getAmount());
        }
    }

    /**
     * @author wurenhai
     * @date 2018/1/4
     */
    @Component
    @Singleton
    public static class TransactionPreparationCommittedLogHandler extends LogHandler<TransactionPreparationCommitted> {
        @Override
        public void handle(TransactionPreparationCommitted e) {
            TransactionPreparation preparation = e.getTransactionPreparation();
            LOG.info("id={}, version={}, preparation(accountId={}, transactionId={}, transactionType={}, preparationType={}, amount={})",
                    e.getAggregateRootId(), e.getVersion(),
                    preparation.getAccountId(), preparation.getTransactionId(),
                    preparation.getTransactionType(), preparation.getPreparationType(), preparation.getAmount());
        }
    }

    @Component
    @Singleton
    public static class TransferTransactionStartedLogHandler extends LogHandler<TransferTransactionStarted> {
        @Override
        public void handle(TransferTransactionStarted e) {
            TransferTransactionInfo transactionInfo = e.getTransactionInfo();
            LOG.info("id={}, version={}, transactionInfo(source={}, target={}, amount={})",
                    e.getAggregateRootId(), e.getVersion(),
                    transactionInfo.getSourceAccountId(), transactionInfo.getTargetAccountId(), transactionInfo.getAmount());
        }
    }

}
