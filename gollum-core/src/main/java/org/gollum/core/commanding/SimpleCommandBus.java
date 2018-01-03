package org.gollum.core.commanding;

import org.gollum.core.common.Assertion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public class SimpleCommandBus implements CommandBus, CommandConsumer {

    private final Map<Class<? extends Command>, CommandHandler<? extends Command>> handlers = new HashMap<>();

    @Override
    public void send(Command command) {
        Assertion.notNull(command, "command");
        consume(command);
    }

    @Override
    public void register(Class<? extends Command> type, CommandHandler<? extends Command> handler) {
        Assertion.notNull(handler, "handler");
        handlers.put(type, handler);
    }

    @Override
    public void consume(Command command) {
        CommandHandler handler = findCommandHandlerFor(command);
        Assertion.notNull(handler, "handler");
        handler.exec(command);
    }

    private CommandHandler<? extends Command> findCommandHandlerFor(Command command) {
        if (!handlers.containsKey(command.getClass())) {
            return null;
        }
        return handlers.get(command.getClass());
    }

}
