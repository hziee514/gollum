package org.gollum.core.eventing;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public abstract class BaseAggregateSnapshot implements AggregateSnapshot {

    /**
     * 聚合根ID
     */
    private String aggregateRootId;

    /**
     * 聚合根版本号
     */
    private int version;

    /**
     * 快照时间戳
     */
    private long timestamp;

    /**
     *
     * @param aggregateRootId 聚合根ID
     * @param version 聚合根版本号
     */
    public BaseAggregateSnapshot(String aggregateRootId, int version) {
        this(aggregateRootId, version, System.currentTimeMillis());
    }

    /**
     *
     * @param aggregateRootId 聚合根ID
     * @param version 聚合根版本号
     * @param timestamp 快照时间戳
     */
    public BaseAggregateSnapshot(String aggregateRootId, int version, long timestamp) {
        this.aggregateRootId = aggregateRootId;
        this.version =version;
        this.timestamp = timestamp;
    }

    @Override
    public String getAggregateRootId() {
        return aggregateRootId;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

}
