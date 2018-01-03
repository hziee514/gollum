package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public interface CommandBus {

    void send(Command command);

}
