package org.gollum.core.eventing;

import java.util.UUID;

/**
 * 领域事件抽象类
 * 只能由聚合根产生
 * 一个领域事件可以有多个订阅者
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class DomainEvent {

    /**
     * 事件ID
     */
    private final String id;

    /**
     * 时间戳
     */
    private final long timestamp;

    /**
     * 聚合根ID
     */
    private String aggregateRootId;

    /**
     * 事件版本号
     */
    private int version;

    public DomainEvent() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public void setAggregateRootId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
