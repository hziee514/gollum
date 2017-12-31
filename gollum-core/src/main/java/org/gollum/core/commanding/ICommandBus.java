package org.gollum.core.commanding;

import org.gollum.core.commanding.callback.NoOpCallback;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface ICommandBus {

    /*default <T extends Command> void send(T command) {
        send(command, NoOpCallback.INSTANCE);
    }*/

    /*<T extends Command, R> void send(T command, ICommandCallback<T, R> callback);*/

    void send(Command command);

}
