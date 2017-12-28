package org.gollum.note;

import org.gollum.core.commanding.ICommandBus;
import org.gollum.note.command.ChangeNoteTitleCommand;
import org.gollum.note.command.CreateNoteCommand;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.TaskScheduler;

import java.util.Date;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class Application {

    public static void main( String[] args ) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ICommandBus bus = ctx.getBean(ICommandBus.class);

        String aggregateRootId = UUID.randomUUID().toString();
        bus.send(new CreateNoteCommand(aggregateRootId, "hello"));

        bus.send(new ChangeNoteTitleCommand(aggregateRootId, "world"));

        bus.send(new ChangeNoteTitleCommand(aggregateRootId, "change2"));

        bus.send(new ChangeNoteTitleCommand(aggregateRootId, "change3"));

        TaskScheduler scheduler = ctx.getBean(TaskScheduler.class);

        scheduler.schedule(() -> System.out.println(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 1000));
        scheduler.schedule(() -> System.out.println(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 2000));
        System.out.println(System.currentTimeMillis());
    }

}
