package org.gollum.note;

import org.gollum.core.commanding.CommandBus;
import org.gollum.core.commanding.SimpleCommandBus;
import org.gollum.core.eventing.*;
import org.gollum.note.command.ChangeNoteTitleCommand;
import org.gollum.note.command.CreateNoteCommand;
import org.gollum.note.commandhandler.ChangeNoteTitleCommandHandler;
import org.gollum.note.commandhandler.CreateNoteCommandHandler;
import org.gollum.note.eventhandler.NoteEventHandlerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
    CommandBus commandBus(ApplicationContext context) {
        System.out.println("AppConfig.commandBus()");
        SimpleCommandBus bus = new SimpleCommandBus();
        bus.register(CreateNoteCommand.class, context.getBean(CreateNoteCommandHandler.class));
        bus.register(ChangeNoteTitleCommand.class, context.getBean(ChangeNoteTitleCommandHandler.class));
        return bus;
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
