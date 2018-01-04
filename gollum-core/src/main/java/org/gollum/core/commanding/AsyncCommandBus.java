package org.gollum.core.commanding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class AsyncCommandBus extends SimpleCommandBus {

    private final ExecutorService executor;

    public AsyncCommandBus() {
        this.executor = Executors.newSingleThreadExecutor();
    }

    public AsyncCommandBus(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public void consume(final Command command) {
        executor.execute(() -> super.consume(command));
    }

}
