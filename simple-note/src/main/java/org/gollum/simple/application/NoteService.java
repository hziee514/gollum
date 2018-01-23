package org.gollum.simple.application;

import org.gollum.common.util.Assertion;
import org.gollum.common.util.ObjectId;
import org.gollum.common.util.SnowflakeId;
import org.gollum.simple.Singleton;
import org.gollum.simple.note.Note;
import org.gollum.simple.note.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
@Transactional
@Component
@Singleton
public class NoteService {

    private ObjectId<Long> idGen = new SnowflakeId();

    @Autowired
    private NoteRepository repository;

    public void create(String title) {
        Note note = new Note(idGen.newId(), title);
        repository.commit(note);
    }

    public void changeTitle(long id, String title) {
        Note note = repository.getById(id);
        Assertion.notNull(note, "Note[%d]", id);
        note.changeTitle(title);
        repository.commit(note);
    }

}
