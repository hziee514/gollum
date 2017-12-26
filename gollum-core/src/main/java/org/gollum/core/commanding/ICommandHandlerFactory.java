package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface ICommandHandlerFactory {

    ICommandHandler getExecutor(Class<? extends Command> type);

}
