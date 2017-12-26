package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface ICommandHandler<T extends Command> {

    void exec(T command);

}
