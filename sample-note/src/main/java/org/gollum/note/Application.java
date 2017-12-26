package org.gollum.note;

import org.gollum.core.commanding.ICommandBus;
import org.gollum.note.command.ChangeNoteTitleCommand;
import org.gollum.note.command.CreateNoteCommand;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class Application {

    public static void main( String[] args ) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ICommandBus bus = ctx.getBean(ICommandBus.class);

        String aggregateRootId = UUID.randomUUID().toString();
        bus.send(new CreateNoteCommand(aggregateRootId, "hello"));

        bus.send(new ChangeNoteTitleCommand(aggregateRootId, "world"));

        System.out.println("");
    }

}
