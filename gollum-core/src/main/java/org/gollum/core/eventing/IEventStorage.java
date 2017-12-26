package org.gollum.core.eventing;

import org.gollum.core.domain.AggregateRoot;

import java.util.List;

/**
 * 领域事件和快照持久化接口
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public interface IEventStorage {

    /**
     * 读取聚合根的所有历史事件
     *
     * @param aggregateRootId
     * @return
     */
    List<DomainEvent> getEvents(String aggregateRootId);

    /**
     * 读取聚合根指定版本号以后的历史事件
     *
     * @param aggregateRootId
     * @param version
     * @return
     */
    List<DomainEvent> getEvents(String aggregateRootId, int version);

    /**
     * 持久化聚合根状态：未提交的领域事件和快照
     *
     * @param aggregateRoot
     */
    void save(AggregateRoot aggregateRoot);

    /**
     * 读取聚合根快照
     *
     * @param aggregateRootId
     * @param <T>
     * @return
     */
    <T extends AggregateSnapshot> T getSnapshot(String aggregateRootId);

    /**
     * 持久化聚合根快照
     *
     * @param snapshot
     */
    void saveSnapshot(AggregateSnapshot snapshot);

}
