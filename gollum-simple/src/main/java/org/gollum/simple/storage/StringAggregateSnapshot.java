package org.gollum.simple.storage;

/**
 * @author wurenhai
 * @date 2018/1/10
 */
public abstract class StringAggregateSnapshot implements AggregateSnapshot<String> {

    /**
     * 聚合根ID
     */
    private String aggregateRootId;

    public StringAggregateSnapshot(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

}
