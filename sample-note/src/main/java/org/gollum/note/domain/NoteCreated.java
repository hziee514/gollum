package org.gollum.note.domain;

import org.gollum.core.eventing.DomainEvent;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class NoteCreated extends DomainEvent {

    private static final long serialVersionUID = 6620296583520070067L;

    private String title;

    public NoteCreated(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
