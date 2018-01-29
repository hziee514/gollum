package org.gollum.simple.note;

import org.gollum.simple.Singleton;
import org.gollum.simple.domain.LongRepository;
import org.gollum.simple.storage.NoteSnapshot;
import org.gollum.simple.storage.NoteStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
@Component
@Singleton
public class NoteRepository extends LongRepository<NoteSnapshot, Note> {

    @Autowired
    public NoteRepository(NoteStorage storage) {
        injectStorage(storage);
    }

}
