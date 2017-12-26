package org.gollum.note.commandhandler;

import org.gollum.core.commanding.ICommandHandler;
import org.gollum.note.command.CreateNoteCommand;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class CreateNoteCommandHandler implements ICommandHandler<CreateNoteCommand> {

    @Override
    public void exec(CreateNoteCommand command) {
        System.out.println(command.getTitle());
    }

}
