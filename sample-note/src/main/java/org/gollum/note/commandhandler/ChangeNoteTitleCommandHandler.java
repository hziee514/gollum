package org.gollum.note.commandhandler;

import org.gollum.core.commanding.CommandHandler;
import org.gollum.note.command.ChangeNoteTitleCommand;
import org.gollum.note.domain.Note;
import org.gollum.note.domain.NoteAbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
@Component
@Scope("singleton")
public class ChangeNoteTitleCommandHandler implements CommandHandler<ChangeNoteTitleCommand> {

    @Autowired
    private NoteAbstractRepository repository;

    @Override
    public void exec(ChangeNoteTitleCommand command) {
        Note note = repository.getById(command.getAggregateRootId(), Note.class);
        int version = note.getVersion();
        note.changeTitle(command.getTitle());
        repository.commit(note, version);
    }

}
