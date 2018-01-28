package org.gollum.note.domain;

import org.gollum.core.eventing.DomainEvent;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class NoteTitleChanged extends DomainEvent {

    private static final long serialVersionUID = 9131076650243334742L;

    private String title;

    public NoteTitleChanged(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
