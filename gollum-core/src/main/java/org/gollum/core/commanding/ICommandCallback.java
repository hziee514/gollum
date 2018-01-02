package org.gollum.core.commanding;

/**
 * @author wurenhai
 * @date 2017/12/29
 */
@Deprecated
public interface ICommandCallback<T extends Command, R> {

    /**
     *
     * @param command
     * @param result
     */
    void onSuccess(T command, R result);

    /**
     *
     * @param command
     * @param cause
     */
    void onFailure(T command, Throwable cause);

}
