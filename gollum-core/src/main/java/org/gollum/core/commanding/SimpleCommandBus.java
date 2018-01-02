package org.gollum.core.commanding;

import org.gollum.core.common.Assertion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public class SimpleCommandBus implements CommandBus, CommandConsumer {

    private final Map<Class<? extends AbstractCommand>, CommandHandler<? extends AbstractCommand>> handlers = new HashMap<>();

    @Override
    public void send(AbstractCommand command) {
        Assertion.notNull(command, "command");
        consume(command);
    }

    @Override
    public void register(Class<? extends AbstractCommand> type, CommandHandler<? extends AbstractCommand> handler) {
        Assertion.notNull(handler, "handler");
        handlers.put(type, handler);
    }

    @Override
    public void consume(AbstractCommand command) {
        CommandHandler handler = findCommandHandlerFor(command);
        Assertion.notNull(handler, "handler");
        handler.exec(command);
    }

    private CommandHandler<? extends AbstractCommand> findCommandHandlerFor(AbstractCommand command) {
        if (!handlers.containsKey(command.getClass())) {
            return null;
        }
        return handlers.get(command.getClass());
    }

}
