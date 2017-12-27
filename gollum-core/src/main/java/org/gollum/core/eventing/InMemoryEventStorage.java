package org.gollum.core.eventing;

import org.gollum.core.domain.AggregateRoot;
import org.gollum.core.domain.IOriginator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wurenhai
 * @date 2017/12/27
 */
public class InMemoryEventStorage implements IEventStorage {

    private final List<DomainEvent> events = new LinkedList<>();
    private final List<AggregateSnapshot> snapshots = new LinkedList<>();

    @Override
    public List<DomainEvent> getEvents(String aggregateRootId) {
        return getEvents(aggregateRootId, -1);
    }

    @Override
    public List<DomainEvent> getEvents(String aggregateRootId, int version) {
        return events.stream()
                .filter(e -> aggregateRootId.equals(e.getAggregateRootId()) && e.getVersion() > version)
                .sorted(Comparator.comparing(DomainEvent::getVersion))
                .collect(Collectors.toList());
    }

    @Override
    public void save(AggregateRoot aggregateRoot) {
        List<DomainEvent> changes = aggregateRoot.getUncommittedChanges();
        events.addAll(changes);

        //保存最新快照
        AggregateSnapshot snapshot = ((IOriginator)aggregateRoot).takeSnapshot();
        saveSnapshot(snapshot);
    }

    @Override
    public <T extends AggregateSnapshot> T getSnapshot(String aggregateRootId) {
        Optional<AggregateSnapshot> snapshot = snapshots.stream()
                .filter(a -> aggregateRootId.equals(a.getAggregateRootId()))
                .max(Comparator.comparing(AggregateSnapshot::getVersion));
        if (!snapshot.isPresent()) {
            return null;
        }
        return (T)snapshot.get();
    }

    @Override
    public void saveSnapshot(AggregateSnapshot snapshot) {
        snapshots.removeIf(a -> a.getAggregateRootId().equals(snapshot.getAggregateRootId()));
        snapshots.add(snapshot);
    }

}
