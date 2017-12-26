package org.gollum.core.eventing;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface IEventPublisher {

    void publish(DomainEvent event);

}
