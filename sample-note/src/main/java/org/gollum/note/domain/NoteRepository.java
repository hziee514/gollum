package org.gollum.note.domain;

import org.gollum.core.domain.Repository;
import org.gollum.core.eventing.IEventPublisher;
import org.gollum.core.eventing.IEventStorage;
import org.gollum.note.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2017/12/27
 */
@Component
@Singleton
public class NoteRepository extends Repository<Note> {

    @Autowired
    public NoteRepository(IEventStorage storage, IEventPublisher publisher) {
        super(storage, publisher);
    }

}
