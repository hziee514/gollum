package org.gollum.note.commandhandler;

import org.gollum.core.commanding.ICommandHandler;
import org.gollum.note.command.CreateNoteCommand;
import org.gollum.note.domain.Note;
import org.gollum.note.domain.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
@Component
@Scope("singleton")
public class CreateNoteCommandHandler implements ICommandHandler<CreateNoteCommand> {

    @Autowired
    private NoteRepository repository;

    @Override
    public void exec(CreateNoteCommand command) {
        Note note = new Note(command.getAggregateRootId(), command.getTitle());
        repository.save(note, -1);
    }

}
