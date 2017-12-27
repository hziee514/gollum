package org.gollum.core.commanding;

import org.gollum.core.infrastructure.Message;

/**
 * 业务命令抽象类
 * 每个Command有一个Handler
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class Command extends Message {

    /**
     * 聚合根ID
     */
    private String aggregateRootId;

    /**
     * 聚合根版本号
     */
    private int version;

    public Command(String aggregateRootId) {
        super();
        this.aggregateRootId = aggregateRootId;
    }

    public Command(String aggregateRootId, int version) {
        this(aggregateRootId);
        this.version = version;
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public int getVersion() {
        return version;
    }
}
