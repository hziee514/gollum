package org.gollum.core.eventing;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class InMemoryEventPublisher implements IEventPublisher {

    private final IEventHandlerFactory factory;

    public InMemoryEventPublisher(IEventHandlerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void publish(DomainEvent event) {
        Class<? extends DomainEvent> type = event.getClass();
        factory.getHandlers(type).stream()
                .filter(handler -> {
                    try {
                        handler.getClass().getMethod("handle", type);
                        return true;
                    } catch (NoSuchMethodException e) {
                        return false;
                    }
                })
                .forEach(handler -> handler.handle(event));
    }

}
