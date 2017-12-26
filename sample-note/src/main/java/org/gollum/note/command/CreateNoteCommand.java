package org.gollum.note.command;

import org.gollum.core.commanding.Command;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class CreateNoteCommand extends Command {

    private String title;

    public CreateNoteCommand(String aggregateRootId, String title) {
        super(aggregateRootId);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
