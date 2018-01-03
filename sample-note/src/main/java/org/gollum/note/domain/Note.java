package org.gollum.note.domain;

import org.gollum.core.domain.BaseAggregateRoot;
import org.gollum.core.domain.AggregateMemento;
import org.gollum.core.eventing.AggregateSnapshot;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class Note extends BaseAggregateRoot implements AggregateMemento {

    private String title;

    public Note() {

    }

    public Note(String id, String title) {
        super(id);
        applyChange(new NoteCreated(title));
    }

    public void changeTitle(String title) {
        applyChange(new NoteTitleChanged(title));
    }

    public String getTitle() {
        return title;
    }

    @Override
    public AggregateSnapshot takeSnapshot() {
        return new NoteSnapshot(this.id, this.version, this.title);
    }

    @Override
    public void restoreFromSnapshot(AggregateSnapshot snapshot) {
        NoteSnapshot ss = (NoteSnapshot)snapshot;
        this.id = ss.getAggregateRootId();
        this.version = ss.getVersion();
        this.title = ss.getTitle();
    }

    public void handle(NoteCreated e) {
        this.title = e.getTitle();
    }

    public void handle(NoteTitleChanged e) {
        this.title = e.getTitle();
    }

}
