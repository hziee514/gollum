package org.gollum.core.eventing;

import java.util.Collection;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface IEventHandlerFactory {

    Collection<IEventHandler> getHandlers(Class<? extends DomainEvent> type);

}
