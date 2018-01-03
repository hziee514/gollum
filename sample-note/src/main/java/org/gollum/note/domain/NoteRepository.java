package org.gollum.note.domain;

import org.gollum.core.domain.BaseRepository;
import org.gollum.core.eventing.EventBus;
import org.gollum.core.eventing.EventStorage;
import org.gollum.note.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2017/12/27
 */
@Component
@Singleton
public class NoteRepository extends BaseRepository<Note> {

    @Autowired
    public NoteRepository(EventStorage storage, EventBus publisher) {
        super(storage, publisher);
    }

}
