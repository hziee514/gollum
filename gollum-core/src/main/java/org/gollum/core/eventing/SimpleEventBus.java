package org.gollum.core.eventing;

import org.gollum.common.util.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public class SimpleEventBus implements EventBus, EventConsumer {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final Map<Class<? extends DomainEvent>, Set<EventHandler<? extends DomainEvent>>> handlers = new HashMap<>();

    private final ExecutorService executor;

    public SimpleEventBus() {
        this(Executors.newFixedThreadPool(2));
    }

    public SimpleEventBus(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public void publish(DomainEvent event) {
        Assertion.notNull(event, "event");
        executor.execute(() -> consume(event));
    }

    @Override
    public void register(Class<? extends DomainEvent> type, EventHandler<? extends DomainEvent> handler) {
        Assertion.notNull(handler, "handler");
        Set set = findHandlersFor(type);
        set.add(handler);
    }

    @Override
    public <T extends DomainEvent> void consume(T e) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("consume: {}", e);
        }
        for (EventHandler handler : findHandlersFor(e.getClass())) {
            handler.handle(e);
        }
    }

    private Set<EventHandler<? extends DomainEvent>> findHandlersFor(Class<? extends DomainEvent> type) {
        if (handlers.containsKey(type)) {
            return handlers.get(type);
        } else {
            Set<EventHandler<? extends DomainEvent>> set = new HashSet<>();
            handlers.put(type, set);
            return set;
        }
    }

}
