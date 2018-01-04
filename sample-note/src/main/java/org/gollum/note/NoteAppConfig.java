package org.gollum.note;

import org.gollum.core.commanding.CommandBus;
import org.gollum.core.commanding.SimpleCommandBus;
import org.gollum.core.eventing.EventBus;
import org.gollum.core.eventing.EventStorage;
import org.gollum.core.eventing.InMemoryEventStorage;
import org.gollum.core.eventing.SimpleEventBus;
import org.gollum.core.scheduling.SimpleTaskScheduler;
import org.gollum.core.scheduling.TinyScheduler;
import org.gollum.note.command.ChangeNoteTitleCommand;
import org.gollum.note.command.CreateNoteCommand;
import org.gollum.note.commandhandler.ChangeNoteTitleCommandHandler;
import org.gollum.note.commandhandler.CreateNoteCommandHandler;
import org.gollum.note.domain.NoteCreated;
import org.gollum.note.domain.NoteTitleChanged;
import org.gollum.note.eventhandler.NoteCreatedHandler;
import org.gollum.note.eventhandler.NoteTitleChangedHandler;
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
public class NoteAppConfig {

    @Bean
    @Singleton
    CommandBus commandBus(ApplicationContext context) {
        System.out.println("NoteAppConfig.commandBus()");
        SimpleCommandBus bus = new SimpleCommandBus();
        bus.register(CreateNoteCommand.class, context.getBean(CreateNoteCommandHandler.class));
        bus.register(ChangeNoteTitleCommand.class, context.getBean(ChangeNoteTitleCommandHandler.class));
        return bus;
    }

    @Bean
    @Singleton
    EventStorage eventStorage() {
        return new InMemoryEventStorage();
    }

    @Bean
    @Singleton
    EventBus eventBus(ApplicationContext context) {
        SimpleEventBus bus = new SimpleEventBus();
        bus.register(NoteCreated.class, context.getBean(NoteCreatedHandler.class));
        bus.register(NoteTitleChanged.class, context.getBean(NoteTitleChangedHandler.class));
        return bus;
    }

    @Bean
    @Singleton
    TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2);
        return scheduler;
    }

    @Bean
    @Singleton
    TinyScheduler tinyScheduler() {
        return new SimpleTaskScheduler();
    }

}
