package org.gollum.note.eventhandler;

import com.google.gson.Gson;
import org.gollum.core.eventing.IEventHandler;
import org.gollum.note.domain.NoteTitleChanged;

/**
 * @author wurenhai
 * @date 2017/12/27
 */
public class NoteTitleChangedHandler implements IEventHandler<NoteTitleChanged> {

    private final ThreadLocal<Gson> gson = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new Gson();
        }
    };

    @Override
    public void handle(NoteTitleChanged event) {
        System.out.println(gson.get().toJson(event));
    }
}
