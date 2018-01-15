package org.gollum.core.commanding;

import org.gollum.common.exception.GollumException;
import org.gollum.common.util.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public class SimpleCommandBus implements CommandBus, CommandConsumer {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final Map<Class<? extends Command>, CommandHandler<? extends Command>> handlers = new HashMap<>();

    private final ExecutorService executor;

    public SimpleCommandBus() {
        this(Executors.newSingleThreadExecutor());
    }

    public SimpleCommandBus(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public Future<?> send(final Command command) {
        Assertion.notNull(command, "command");
        return executor.submit(() -> {
            try {
                consume(command);
                return true;
            } catch (GollumException e) {
                LOG.error(e.getMessage(), e);
                return false;
            }
        });
    }

    @Override
    public void register(Class<? extends Command> type, CommandHandler<? extends Command> handler) {
        Assertion.notNull(handler, "handler");
        handlers.put(type, handler);
    }

    @Override
    public void consume(Command command) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("consume: {}", command.toString());
        }
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
