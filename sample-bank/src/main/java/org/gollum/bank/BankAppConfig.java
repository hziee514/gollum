package org.gollum.bank;

import org.gollum.bank.command.*;
import org.gollum.bank.commandhandler.*;
import org.gollum.bank.domain.account.BankAccountCreated;
import org.gollum.bank.domain.account.TransactionPreparationAdded;
import org.gollum.bank.domain.account.TransactionPreparationCommitted;
import org.gollum.bank.domain.deposit.DepositTransactionCompleted;
import org.gollum.bank.domain.deposit.DepositTransactionPreparationCompleted;
import org.gollum.bank.domain.deposit.DepositTransactionStarted;
import org.gollum.bank.eventhandler.LogHandler;
import org.gollum.bank.saga.deposit.DepositTransactionPreparationCompletedSagaHandler;
import org.gollum.bank.saga.deposit.DepositTransactionStartedSagaHandler;
import org.gollum.bank.saga.deposit.TransactionPreparationAddedSagaHandler;
import org.gollum.bank.saga.deposit.TransactionPreparationCommittedSagaHandler;
import org.gollum.core.commanding.CommandBus;
import org.gollum.core.commanding.SimpleCommandBus;
import org.gollum.core.eventing.EventBus;
import org.gollum.core.eventing.EventStorage;
import org.gollum.core.eventing.InMemoryEventStorage;
import org.gollum.core.eventing.SimpleEventBus;
import org.gollum.core.scheduling.SimpleTaskScheduler;
import org.gollum.core.scheduling.TinyScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
@Configuration
@ComponentScan
public class BankAppConfig {

    @Bean
    @Singleton
    CommandBus commandBus(ApplicationContext ctx) {
        SimpleCommandBus bus = new SimpleCommandBus(Executors.newFixedThreadPool(2));
        bus.register(CreateAccountCommand.class, ctx.getBean(CreateAccountCommandHandler.class));
        bus.register(StartDepositTransactionCommand.class, ctx.getBean(StartDepositTransactionCommandHandler.class));
        bus.register(AddTransactionPreparationCommand.class, ctx.getBean(AddTransactionPreparationCommandHandler.class));
        bus.register(ConfirmDepositPreparationCommand.class, ctx.getBean(ConfirmDepositPreparationCommandHandler.class));
        bus.register(CommitTransactionPreparationCommand.class, ctx.getBean(CommitTransactionPreparationCommandHandler.class));
        bus.register(ConfirmDepositCommand.class, ctx.getBean(ConfirmDepositCommandHandler.class));
        return bus;
    }

    @Bean
    @Singleton
    EventStorage eventStorage() {
        return new InMemoryEventStorage();
    }

    @Bean
    @Singleton
    EventBus eventBus(ApplicationContext ctx) {
        SimpleEventBus bus = new SimpleEventBus();
        bus.register(BankAccountCreated.class, ctx.getBean(LogHandler.BankAccountCreatedLogHandler.class));

        bus.register(DepositTransactionStarted.class, ctx.getBean(LogHandler.DepositTransactionStartedLogHandler.class));
        bus.register(DepositTransactionStarted.class, ctx.getBean(DepositTransactionStartedSagaHandler.class));

        bus.register(TransactionPreparationAdded.class, ctx.getBean(LogHandler.TransactionPreparationAddedLogHandler.class));
        bus.register(TransactionPreparationAdded.class, ctx.getBean(TransactionPreparationAddedSagaHandler.class));

        bus.register(DepositTransactionPreparationCompleted.class, ctx.getBean(LogHandler.DepositTransactionPreparationCompletedLogHandler.class));
        bus.register(DepositTransactionPreparationCompleted.class, ctx.getBean(DepositTransactionPreparationCompletedSagaHandler.class));

        bus.register(TransactionPreparationCommitted.class, ctx.getBean(LogHandler.TransactionPreparationCommittedLogHandler.class));
        bus.register(TransactionPreparationCommitted.class, ctx.getBean(TransactionPreparationCommittedSagaHandler.class));

        bus.register(DepositTransactionCompleted.class, ctx.getBean(LogHandler.DepositTransactionCompletedLogHandler.class));

        return bus;
    }

    @Bean
    @Singleton
    TinyScheduler tinyScheduler() {
        return new SimpleTaskScheduler();
    }

}
