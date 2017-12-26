package org.gollum.note.commandhandler;

import org.gollum.core.commanding.ICommandHandler;
import org.gollum.note.command.ChangeNoteTitleCommand;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class ChangeNoteTitleCommandHandler implements ICommandHandler<ChangeNoteTitleCommand> {

    @Override
    public void exec(ChangeNoteTitleCommand command) {
        System.out.println(command.getTitle());
    }

}
