package org.gollum.core.commanding;

import org.gollum.common.exception.GollumException;

/**
 * @author wurenhai
 * @date 2018/1/13
 */
public class CommandExecutionException extends GollumException {
    public CommandExecutionException(Command command, Throwable cause) {
        super(command.getClass().getName(), cause);
    }
}
