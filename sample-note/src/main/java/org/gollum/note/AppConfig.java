package org.gollum.note;

import org.gollum.core.commanding.ICommandBus;
import org.gollum.core.commanding.ICommandHandlerFactory;
import org.gollum.core.commanding.InMemoryCommandBus;
import org.gollum.core.eventing.*;
import org.gollum.note.commandhandler.NoteCommandHandlerFactory;
import org.gollum.note.eventhandler.NoteEventHandlerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    @Singleton
    ICommandBus commandBus(ICommandHandlerFactory factory) {
        return new InMemoryCommandBus(factory);
    }

    @Bean
    @Singleton
    ICommandHandlerFactory commandExecutorFactory(ApplicationContext context) {
        return new NoteCommandHandlerFactory(context);
    }

    @Bean
    @Singleton
    IEventStorage eventStorage() {
        return new InMemoryEventStorage();
    }

    @Bean
    @Singleton
    IEventPublisher eventPublisher(IEventHandlerFactory factory) {
        return new InMemoryEventPublisher(factory);
    }

    @Bean
    @Singleton
    IEventHandlerFactory eventHandlerFactory() {
        return new NoteEventHandlerFactory();
    }

    @Bean
    @Singleton
    TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2);
        return scheduler;
    }

}
