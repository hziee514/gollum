package org.gollum.note.commandhandler;

import org.gollum.core.commanding.Command;
import org.gollum.core.commanding.ICommandHandler;
import org.gollum.core.commanding.ICommandHandlerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class NoteCommandHandlerFactory implements ICommandHandlerFactory {

    private final List<ICommandHandler> handlers = new LinkedList<>();

    public NoteCommandHandlerFactory() {
        handlers.add(new CreateNoteCommandHandler());
        handlers.add(new ChangeNoteTitleCommandHandler());
    }

    @Override
    public ICommandHandler getExecutor(Class<? extends Command> type) {
        return handlers.stream()
                .filter(handler -> {
                    try {
                        handler.getClass().getMethod("exec", type);
                        return true;
                    } catch (NoSuchMethodException e) {
                        return false;
                    }
                })
                .findFirst()
                .get();
    }

}
