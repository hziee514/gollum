package org.gollum.core.scheduling;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public interface TinyScheduler {

    /**
     * 添加一个任务,将在delay(ms)后执行
     *
     * @param name
     * @param task
     * @param delay
     */
    default Future<?> schedule(String name, Runnable task, long delay) {
        return schedule(name, task, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 添加一个任务,将在指定时间后执行
     *
     * @param name
     * @param task
     * @param delay
     * @param unit
     */
    Future<?> schedule(String name, Runnable task, long delay, TimeUnit unit);

    /**
     * 取消延时任务
     *
     * @param name
     */
    void cancel(String name);

}
