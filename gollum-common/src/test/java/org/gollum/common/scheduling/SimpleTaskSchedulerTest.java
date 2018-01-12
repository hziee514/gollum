package org.gollum.common.scheduling;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author wurenhai
 * @date 2018/1/12
 */
public class SimpleTaskSchedulerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test_simpleTaskScheduler() throws Exception {
        SimpleTaskScheduler scheduler = new SimpleTaskScheduler();
        String name1 = "hello";
        Runnable task1 = mock(Runnable.class);
        scheduler.schedule(name1, task1, 15);
        String name2 = "hello2";
        Runnable task2 = mock(Runnable.class);
        scheduler.schedule(name2, task2, 10);
        Thread.sleep(10);
        scheduler.cancel(name1);
        Thread.sleep(10);
        verify(task1, times(0)).run();
        verify(task2, times(1)).run();
    }

    @Test(expected = TaskExistsException.class)
    public void schedule_twice() throws Exception {
        SimpleTaskScheduler scheduler = new SimpleTaskScheduler();
        String name = "hello";
        Runnable task = mock(Runnable.class);
        scheduler.schedule(name, task, 10);
        scheduler.schedule(name, task, 10);
    }

    @Test
    public void cancel_done() throws Exception {
        SimpleTaskScheduler scheduler = new SimpleTaskScheduler();
        String name = "hello";
        Runnable task = mock(Runnable.class);
        Future<?> future = scheduler.schedule(name, task, 10);
        Thread.sleep(20);
        assertTrue(future.isDone());
        verify(task, times(1)).run();
        assertTrue(!scheduler.has(name));
    }

}