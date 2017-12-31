package org.gollum.core.commanding.callback;

import org.gollum.core.commanding.Command;
import org.gollum.core.commanding.ICommandCallback;

/**
 * @author wurenhai
 * @date 2017/12/29
 */
public final class NoOpCallback<T extends Command> implements ICommandCallback<T, Object> {

    public static final NoOpCallback INSTANCE = new NoOpCallback();

    @Override
    public void onSuccess(T command, Object result) {

    }

    @Override
    public void onFailure(T command, Throwable cause) {

    }

}
