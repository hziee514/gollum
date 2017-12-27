package org.gollum.note.domain;

import org.gollum.core.eventing.DomainEvent;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class NoteCreated extends DomainEvent {

    private String title;

    public NoteCreated(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
