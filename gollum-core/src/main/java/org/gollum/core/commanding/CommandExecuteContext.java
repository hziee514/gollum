package org.gollum.core.commanding;

import org.gollum.core.domain.AggregateRoot;
import org.gollum.core.domain.Repository;
import org.gollum.core.common.Assertion;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wurenhai
 * @date 2017/12/28
 */
public class CommandExecuteContext implements ICommandContext {

    private final Map<String, AggregateRoot> trackingAggregateRootDict = new ConcurrentHashMap<>();
    private final Repository repository;

    public CommandExecuteContext(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void add(AggregateRoot aggregateRoot) {
        Assertion.notNull(aggregateRoot, "aggregateRoot");
        trackingAggregateRootDict.put(aggregateRoot.getId(), aggregateRoot);
    }

    @Override
    public <T extends AggregateRoot> T get(String aggregateRootId, Class<T> type) {
        Assertion.notNullOrEmpty(aggregateRootId, "aggregateRootId");
        T aggregateRoot = (T)repository.getById(aggregateRootId, type);
        if (aggregateRoot != null) {
            trackingAggregateRootDict.put(aggregateRootId, aggregateRoot);
        }
        return aggregateRoot;
    }

}
