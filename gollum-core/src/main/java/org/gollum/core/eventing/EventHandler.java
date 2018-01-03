package org.gollum.core.eventing;

/**
 * 领域事件订阅者
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public interface EventHandler<T extends DomainEvent> {

    /**
     * @param event
     */
    void handle(T event);

}
