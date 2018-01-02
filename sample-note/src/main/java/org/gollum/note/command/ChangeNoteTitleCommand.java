package org.gollum.note.command;

import org.gollum.core.commanding.AbstractCommand;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class ChangeNoteTitleCommand extends AbstractCommand {

    private String title;

    public ChangeNoteTitleCommand(String aggregateRootId, String title) {
        super(aggregateRootId);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
