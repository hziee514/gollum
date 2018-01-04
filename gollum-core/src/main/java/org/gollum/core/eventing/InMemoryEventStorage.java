package org.gollum.core.eventing;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wurenhai
 * @date 2017/12/27
 */
public class InMemoryEventStorage implements EventStorage {

    private final List<DomainEvent> events = new LinkedList<>();
    private final List<AggregateSnapshot> snapshots = new LinkedList<>();

    @Override
    public List<DomainEvent> readEvents(String aggregateRootId) {
        return readEvents(aggregateRootId, -1);
    }

    @Override
    public List<DomainEvent> readEvents(String aggregateRootId, int version) {
        return events.stream()
                .filter(e -> aggregateRootId.equals(e.getAggregateRootId()) && e.getVersion() > version)
                .sorted(Comparator.comparing(DomainEvent::getVersion))
                .collect(Collectors.toList());
    }

    @Override
    public void save(List<DomainEvent> events, AggregateSnapshot snapshot) {
        this.events.addAll(events);
        saveSnapshot(snapshot);
    }

    @Override
    public <T extends AggregateSnapshot> T readSnapshot(String aggregateRootId) {
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
