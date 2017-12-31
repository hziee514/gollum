package org.gollum.core.commanding.callback;

import org.gollum.core.commanding.Command;
import org.gollum.core.commanding.ICommandCallback;

/**
 * @author wurenhai
 * @date 2017/12/29
 */
public abstract class VoidCallback<T extends Command> implements ICommandCallback<T, Object> {

    @Override
    public void onSuccess(T command, Object result) {
        onSuccess(command);
    }

    /**
     *
     * @param command
     */
    protected abstract void onSuccess(T command);

}
