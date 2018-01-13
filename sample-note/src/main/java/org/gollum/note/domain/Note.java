package org.gollum.note.domain;

import org.gollum.core.domain.BaseAggregateRoot;
import org.gollum.core.domain.AggregateOriginator;
import org.gollum.core.eventing.AggregateSnapshot;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class Note extends BaseAggregateRoot implements AggregateOriginator {

    private String title;

    private Note() {

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

    private void handle(NoteCreated e) {
        this.title = e.getTitle();
    }

    private void handle(NoteTitleChanged e) {
        this.title = e.getTitle();
    }

}
