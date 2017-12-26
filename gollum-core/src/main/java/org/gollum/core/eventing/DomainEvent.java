package org.gollum.core.eventing;

import org.gollum.core.infrastructure.Message;

/**
 * 领域事件抽象类
 * 只能由聚合根产生
 * 一个领域事件可以有多个订阅者
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class DomainEvent extends Message {

    /**
     * 聚合根ID
     */
    private String aggregateRootId;

    /**
     * 事件版本号
     */
    private int version;

    public DomainEvent() {
        super();
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
