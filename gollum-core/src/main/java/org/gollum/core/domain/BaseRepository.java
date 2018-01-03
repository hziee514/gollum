package org.gollum.core.domain;

import org.gollum.core.eventing.AggregateSnapshot;
import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.EventBus;
import org.gollum.core.eventing.EventStorage;

import java.util.List;

/**
 * 聚合根仓储抽象类, 只能从仓储中读取数据
 * 每个聚合根对应一个Repository
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public class BaseRepository<T extends BaseAggregateRoot> implements Repository<T> {

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
     * @param type
     * @return
     */
    @Override
    public T getById(String aggregateRootId, Class<? extends BaseAggregateRoot> type) {
        List<DomainEvent> events;
        AggregateSnapshot snapshot = storage.readSnapshot(aggregateRootId);
        if (snapshot == null) {
            events = storage.readEvents(aggregateRootId);
        } else {
            events = storage.readEvents(aggregateRootId, snapshot.getVersion());
        }

        try {
            T instance = (T)type.newInstance();
            if (snapshot != null) {
                ((AggregateMemento)instance).restoreFromSnapshot(snapshot);
            }
            if (!events.isEmpty()) {
                instance.replayEvents(events);
            }
            return instance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交聚合根的变更
     *
     * @param aggregateRoot
     * @param expectedVersion 期望修订的版本号, -1表示是新建
     */
    @Override
    public void commit(BaseAggregateRoot aggregateRoot, int expectedVersion) {
        List<DomainEvent> changes = aggregateRoot.getChanges();
        if (changes.isEmpty()) {
            return;
        }
        //TODO: 同步加锁
        if (expectedVersion != -1) {
            BaseAggregateRoot item = getById(aggregateRoot.getId(), aggregateRoot.getClass());
            if (item.getVersion() != expectedVersion) {
                throw new IllegalStateException();
            }
        }

        //存储领域事件和聚合根快照
        AggregateSnapshot snapshot = ((AggregateMemento)aggregateRoot).takeSnapshot();
        storage.save(changes, snapshot);

        //完成改变并发布领域事件
        aggregateRoot.acceptChanges();
        changes.stream().forEach(e -> publisher.publish(e));
    }

}
