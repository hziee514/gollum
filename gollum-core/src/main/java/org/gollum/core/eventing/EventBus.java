package org.gollum.core.eventing;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface EventBus {

    void publish(DomainEvent event);

}
