package org.gollum.note.eventhandler;

import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.IEventHandler;
import org.gollum.core.eventing.IEventHandlerFactory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wurenhai
 * @date 2017/12/27
 */
public class NoteEventHandlerFactory implements IEventHandlerFactory {

    private final List<IEventHandler> handlers = new LinkedList<>();

    public NoteEventHandlerFactory() {
        handlers.add(new NoteCreatedHandler());
        handlers.add(new NoteTitleChangedHandler());
    }

    @Override
    public Collection<IEventHandler> getHandlers(Class<? extends DomainEvent> type) {
        return handlers.stream()
                .filter(handler -> {
                    try {
                        handler.getClass().getMethod("handle", type);
                        return true;
                    } catch (NoSuchMethodException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
