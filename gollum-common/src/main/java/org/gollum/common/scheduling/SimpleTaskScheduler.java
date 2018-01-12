package org.gollum.common.scheduling;

import org.gollum.common.util.Assertion;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public class SimpleTaskScheduler implements TinyScheduler {

    private final ScheduledExecutorService executor;
    private final Map<String, Future<?>> tasks = new ConcurrentHashMap<>();

    public SimpleTaskScheduler() {
        this(Executors.newScheduledThreadPool(2));
    }

    public SimpleTaskScheduler(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public Future<?> schedule(String name, Runnable task, long delay, TimeUnit unit) {
        Assertion.notNullOrEmpty(name, "name");
        Assertion.notNull(task, "task");
        if (tasks.containsKey(name)) {
            throw new TaskExistsException(name);
        }
        TaskProxy proxy = new TaskProxy(name, task);
        ScheduledFuture<?> future = executor.schedule(proxy, delay, unit);
        tasks.put(name, future);
        return future;
    }

    @Override
    public void cancel(String name) {
        Assertion.notNullOrEmpty(name, "name");
        Future<?> future = tasks.remove(name);
        if (future != null && !future.isCancelled() && !future.isDone()) {
            future.cancel(false);
        }
    }

    public boolean has(String name) {
        Assertion.notNullOrEmpty(name, "name");
        return tasks.containsKey(name);
    }

    private void done(String name) {
        Assertion.notNullOrEmpty(name, "name");
        tasks.remove(name);
    }

    private class TaskProxy implements Runnable {

        private final String name;
        private final Runnable target;

        public TaskProxy(String name, Runnable target) {
            this.name = name;
            this.target = target;
        }

        @Override
        public void run() {
            target.run();
            done(name);
        }

    }

}
