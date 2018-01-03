package org.gollum.note.eventhandler;

import com.google.gson.Gson;
import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.EventHandler;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public abstract class LoggingEventHandler<T extends DomainEvent> implements EventHandler<T> {

    private final ThreadLocal<Gson> gson = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new Gson();
        }
    };

    @Override
    public void handle(T event) {
        System.out.println(gson.get().toJson(event));
    }

}
