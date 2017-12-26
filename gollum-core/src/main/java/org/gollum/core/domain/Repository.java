package org.gollum.core.domain;

import org.gollum.core.eventing.AggregateSnapshot;
import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.IEventStorage;

import java.util.List;

/**
 * 聚合根仓储抽象类
 * 每个聚合根对应一个Repository
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class Repository<T extends AggregateRoot> {

    private final IEventStorage storage;

    public Repository(IEventStorage storage) {
        this.storage = storage;
    }

    public T getById(String aggregateRootId, Class<? extends AggregateRoot> type) {
        List<DomainEvent> events;
        AggregateSnapshot snapshot = storage.getSnapshot(aggregateRootId);
        if (snapshot == null) {
            events = storage.getEvents(aggregateRootId);
        } else {
            events = storage.getEvents(aggregateRootId, snapshot.getVersion());
        }

        try {
            T instance = (T)type.newInstance();
            if (snapshot != null) {
                ((IOriginator)instance).restoreFromSnapshot(snapshot);
            }
            instance.replayEvents(events);
            return instance;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(AggregateRoot aggregateRoot, int expectedVersion) {
        if (aggregateRoot.getUncommittedChanges().isEmpty()) {
            return;
        }
        //TODO: 同步锁
        if (expectedVersion == -1) {
            AggregateRoot item = getById(aggregateRoot.getId(), aggregateRoot.getClass());
            if (item.getVersion() != expectedVersion) {
                throw new IllegalStateException();
            }
        }
        storage.save(aggregateRoot);
        aggregateRoot.acceptChanges();
    }

}
