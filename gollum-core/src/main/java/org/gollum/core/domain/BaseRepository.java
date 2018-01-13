package org.gollum.core.domain;

import org.gollum.common.exception.ConcurrencyException;
import org.gollum.common.exception.NoDefaultConstructorException;
import org.gollum.core.eventing.AggregateSnapshot;
import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.EventBus;
import org.gollum.core.eventing.EventStorage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 聚合根仓储抽象类, 只能从仓储中读取数据
 * 每个聚合根对应一个Repository
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class BaseRepository<T extends BaseAggregateRoot> implements Repository<T> {

    private final EventStorage storage;
    private final EventBus publisher;

    public BaseRepository(EventStorage storage, EventBus publisher) {
        this.storage = storage;
        this.publisher = publisher;
    }

    /**
     * 根据ID与类型获取聚合根实例
     *
     * @param aggregateRootId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getById(String aggregateRootId) {
        List<DomainEvent> events;
        AggregateSnapshot snapshot = storage.readSnapshot(aggregateRootId);
        if (snapshot == null) {
            events = storage.readEvents(aggregateRootId);
        } else {
            events = storage.readEvents(aggregateRootId, snapshot.getVersion());
        }

        //既没有快照也没有历史事件时认为不存在此聚合根
        if (snapshot == null && events.isEmpty()) {
            return null;
        }

        Class<T> aggregateRootType = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        try {
            Constructor<T> constructor = aggregateRootType.getDeclaredConstructor(new Class<?>[]{});
            constructor.setAccessible(true);
            T instance = constructor.newInstance();
            if (snapshot != null) {
                ((AggregateOriginator)instance).restoreFromSnapshot(snapshot);
            }
            if (!events.isEmpty()) {
                instance.replayEvents(events);
            }
            return instance;
        } catch (IllegalAccessException e) {
            throw new NoDefaultConstructorException(aggregateRootType.getName());
        } catch (InstantiationException e) {
            throw new NoDefaultConstructorException(aggregateRootType.getName());
        } catch (NoSuchMethodException e) {
            throw new NoDefaultConstructorException(aggregateRootType.getName());
        } catch (InvocationTargetException e) {
            throw new NoDefaultConstructorException(aggregateRootType.getName());
        }
    }

    private static Object locker = new Object();

    /**
     * 提交聚合根的变更
     *
     * @param aggregateRoot
     * @param expectedVersion 期望修订的版本号, -1表示是新建
     */
    @Override
    public void commit(T aggregateRoot, int expectedVersion) {
        List<DomainEvent> changes = aggregateRoot.getChanges();
        if (changes.isEmpty()) {
            return;
        }

        //TODO: 对同一个聚合根要同步加锁或者事物控制, 在CommandConsumer中加锁也可以
        //TODO: 在此之前使用mailbox将对同一个聚合根的操作转为串行处理也可以
        synchronized (locker) {
            if (expectedVersion != -1) {
                T item = getById(aggregateRoot.getId());
                if (item.getVersion() != expectedVersion) {
                    throw new ConcurrencyException("id=%s, version=%d, expected=%d",
                            aggregateRoot.getId(), aggregateRoot.getVersion(), expectedVersion);
                }
            }

            //存储领域事件和聚合根快照
            AggregateSnapshot snapshot = ((AggregateOriginator) aggregateRoot).takeSnapshot();
            storage.save(changes, snapshot);

            aggregateRoot.acceptChanges();
        }

        //完成改变并发布领域事件
        changes.stream().forEach(e -> publisher.publish(e));
    }

}
