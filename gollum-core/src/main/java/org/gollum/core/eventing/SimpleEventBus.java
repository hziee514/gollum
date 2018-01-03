package org.gollum.core.eventing;

import org.gollum.core.common.Assertion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public class SimpleEventBus implements EventBus, EventConsumer {

    private final Map<Class<? extends DomainEvent>, EventHandler<? extends DomainEvent>> handlers = new HashMap<>();

    @Override
    public void publish(DomainEvent event) {
        Assertion.notNull(event, "event");
        consume(event);
    }

    @Override
    public void register(Class<? extends DomainEvent> type, EventHandler<? extends DomainEvent> handler) {
        Assertion.notNull(handler, "handler");
        handlers.put(type, handler);
    }

    @Override
    public <T extends DomainEvent> void consume(T e) {
        EventHandler handler = findHandlerFor(e);
        Assertion.notNull(handler, "handler");
        handler.handle(e);
    }

    private EventHandler<? extends DomainEvent> findHandlerFor(DomainEvent e) {
        if (!handlers.containsKey(e.getClass())) {
            return null;
        }
        return handlers.get(e.getClass());
    }

}
