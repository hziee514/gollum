package org.gollum.core.commanding;

import java.util.UUID;

/**
 * 业务命令抽象类
 * 每个Command有一个Handler
 *
 * @author wurenhai
 * @date 2018/1/2
 */
public abstract class AbstractCommand implements Command {

    /**
     * Command自身ID
     */
    private final String id;

    /**
     * 聚合根ID
     */
    private final String aggregateRootId;

    /**
     * 聚合根版本号
     */
    private final int aggregateRootVersion;

    public AbstractCommand(String aggregateRootId) {
        this(aggregateRootId, 0);
    }

    public AbstractCommand(String aggregateRootId, int aggregateRootVersion) {
        this.id = UUID.randomUUID().toString();
        this.aggregateRootId = aggregateRootId;
        this.aggregateRootVersion = aggregateRootVersion;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getAggregateRootId() {
        return aggregateRootId;
    }

    @Override
    public int getAggregateRootVersion() {
        return aggregateRootVersion;
    }
}
