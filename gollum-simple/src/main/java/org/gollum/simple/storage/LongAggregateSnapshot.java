package org.gollum.simple.storage;

/**
 * @author wurenhai
 * @date 2018/1/10
 */
public abstract class LongAggregateSnapshot implements AggregateSnapshot<Long> {

    /**
     * 聚合根ID
     */
    private long aggregateRootId;

    public LongAggregateSnapshot(long aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public Long getAggregateRootId() {
        return aggregateRootId;
    }

}
