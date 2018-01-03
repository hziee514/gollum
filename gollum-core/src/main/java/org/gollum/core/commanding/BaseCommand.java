package org.gollum.core.commanding;

import java.util.UUID;

/**
 * 业务命令抽象类
 * 每个Command有一个Handler
 *
 * @author wurenhai
 * @date 2018/1/2
 */
public abstract class BaseCommand implements Command {

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
    private final int version;

    public BaseCommand(String aggregateRootId) {
        this(aggregateRootId, 0);
    }

    public BaseCommand(String aggregateRootId, int version) {
        this.id = UUID.randomUUID().toString();
        this.aggregateRootId = aggregateRootId;
        this.version = version;
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
    public int getVersion() {
        return version;
    }
}
