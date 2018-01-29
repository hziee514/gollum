package org.gollum.simple.storage;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
public class NoteSnapshot extends LongAggregateSnapshot {

    private String title;

    public NoteSnapshot(long aggregateRootId, String title) {
        super(aggregateRootId);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
