package org.gollum.simple.storage;

/**
 * @author wurenhai
 * @date 2018/1/10
 */
public abstract class BaseAggregateSnapshot implements AggregateSnapshot {

    /**
     * 聚合根ID
     */
    private long aggregateRootId;

    public BaseAggregateSnapshot(long aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public long getAggregateRootId() {
        return aggregateRootId;
    }

}
