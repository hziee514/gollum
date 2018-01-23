package org.gollum.simple.note;

import org.gollum.common.util.Assertion;
import org.gollum.simple.domain.BaseAggregateRoot;
import org.gollum.simple.storage.NoteSnapshot;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
public class Note extends BaseAggregateRoot<NoteSnapshot> {

    private String title;

    private Note() {
        //required by repository
    }

    public Note(long id, String title) {
        super(id);
        changeTitle(title);
    }

    public void changeTitle(String title) {
        Assertion.notNullOrEmpty(title, "title");
        this.title = title;
    }

    @Override
    public NoteSnapshot takeSnapshot() {
        return new NoteSnapshot(this.id, this.title);
    }

    @Override
    public void restoreFromSnapshot(NoteSnapshot snapshot) {
        this.id = snapshot.getAggregateRootId();
        this.title = snapshot.getTitle();
    }

}
