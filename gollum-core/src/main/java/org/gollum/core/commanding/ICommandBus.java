package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface ICommandBus {

    void send(Command command);

}
