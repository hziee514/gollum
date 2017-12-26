package org.gollum.note.domain;

import org.gollum.core.eventing.AggregateSnapshot;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class NoteSnapshot extends AggregateSnapshot {

    private String title;

    public NoteSnapshot(String aggregateRootId, int version, String title) {
        super(aggregateRootId, version);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
