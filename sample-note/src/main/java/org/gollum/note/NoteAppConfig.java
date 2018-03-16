package org.gollum.note;

import org.gollum.common.scheduling.SimpleTaskScheduler;
import org.gollum.common.scheduling.TinyScheduler;
import org.gollum.common.serializing.JavaSerializer;
import org.gollum.common.serializing.ObjectSerializer;
import org.gollum.core.commanding.CommandBus;
import org.gollum.core.commanding.SimpleCommandBus;
import org.gollum.core.eventing.*;
import org.gollum.note.command.ChangeNoteTitleCommand;
import org.gollum.note.command.CreateNoteCommand;
import org.gollum.note.commandhandler.ChangeNoteTitleCommandHandler;
import org.gollum.note.commandhandler.CreateNoteCommandHandler;
import org.gollum.note.domain.NoteCreated;
import org.gollum.note.domain.NoteTitleChanged;
import org.gollum.note.eventhandler.NoteCreatedHandler;
import org.gollum.note.eventhandler.NoteTitleChangedHandler;
import org.gollum.rabbitmq.RMQEventBus;
import org.gollum.rabbitmq.RMQEventConsumer;
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

    /*@Bean
    @Singleton
    EventBus eventBus(ApplicationContext ctx) throws Exception {
        RMQEventBus bus = new RMQEventBus(ctx.getBean(ObjectSerializer.class));
        bus.init("amqp://guest:guest@192.168.99.100:5672/gollum", "sample-note-events");
        return bus;
    }

    @Bean
    @Singleton
    EventConsumer eventConsumer(ApplicationContext ctx) throws Exception {
        RMQEventConsumer consumer = new RMQEventConsumer(ctx.getBean(ObjectSerializer.class));
        consumer.init("amqp://guest:guest@192.168.99.100:5672/gollum", "sample-note-events");
        consumer.register(NoteCreated.class, ctx.getBean(NoteCreatedHandler.class));
        consumer.register(NoteTitleChanged.class, ctx.getBean(NoteTitleChangedHandler.class));
        return consumer;
    }*/

    @Bean
    @Singleton
    ObjectSerializer objectSerializer() {
        return new JavaSerializer();
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
