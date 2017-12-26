package org.gollum.core.eventing;

/**
 * 聚合根快照
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class AggregateSnapshot {

    /**
     * 聚合根ID
     */
    private String aggregateRootId;

    /**
     * 聚合根版本号
     */
    private int version;

    /**
     * 时间戳
     */
    private long timestamp;

    public AggregateSnapshot(String aggregateRootId, int version) {
        this.aggregateRootId = aggregateRootId;
        this.version = version;
        this.timestamp = System.currentTimeMillis();
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public int getVersion() {
        return version;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
