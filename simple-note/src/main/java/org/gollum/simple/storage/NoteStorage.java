package org.gollum.simple.storage;

import org.gollum.simple.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
@Component
@Singleton
public class NoteStorage implements LongSnapshotStorage<NoteSnapshot> {

    @Autowired
    private NoteDao dao;

    @Override
    public NoteSnapshot readSnapshot(Long aggregateRootId) {
        NoteBean bean = dao.findOne(aggregateRootId);
        if (bean == null) {
            return null;
        }
        return new NoteSnapshot(bean.getId(), bean.getTitle());
    }

    @Override
    public void saveSnapshot(NoteSnapshot snapshot) {
        NoteBean bean = new NoteBean();
        bean.setId(snapshot.getAggregateRootId());
        bean.setTitle(snapshot.getTitle());
        dao.save(bean);
    }

}
