package org.gollum.note;

import org.gollum.core.commanding.ICommandBus;
import org.gollum.core.commanding.ICommandHandlerFactory;
import org.gollum.core.commanding.InMemoryCommandBus;
import org.gollum.note.commandhandler.NoteCommandHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    @Scope("singleton")
    ICommandBus commandBus(ICommandHandlerFactory factory) {
        return new InMemoryCommandBus(factory);
    }

    @Bean
    @Scope("singleton")
    ICommandHandlerFactory commandExecutorFactory() {
        return new NoteCommandHandlerFactory();
    }

}
