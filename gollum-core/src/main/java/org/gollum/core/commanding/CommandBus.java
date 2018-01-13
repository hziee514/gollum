package org.gollum.core.commanding;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public interface CommandBus {

    /**
     * 发送命令并等待执行完成
     *
     * @param command
     */
    default Object sendSync(Command command) {
        Future<?> future = send(command);
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new CommandExecutionException(command, e);
        } catch (ExecutionException e) {
            throw new CommandExecutionException(command, e);
        }
    }

    /**
     * 发送异步执行命令
     *
     * @param command
     * @return
     */
    Future<?> send(Command command);

}
