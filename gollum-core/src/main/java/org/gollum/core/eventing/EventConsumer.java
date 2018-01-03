package org.gollum.core.eventing;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public interface EventConsumer {

    void register(Class<? extends DomainEvent> type, EventHandler<? extends DomainEvent> handler);

    <T extends DomainEvent> void consume(T e);

}
