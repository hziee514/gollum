package org.gollum.core.domain;

import org.gollum.core.eventing.DomainEvent;

import java.util.List;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public interface AggregateRoot {

    /**
     * 获取聚合根ID
     *
     * @return
     */
    String getId();

    /**
     * 获取聚合根版本号
     *
     * @return
     */
    int getVersion();

    /**
     * 转换聚合根实例类型
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> T as(Class<T> type);

    /**
     * 重放历史事件以恢复状态
     *
     * @param history
     */
    void replayEvents(List<DomainEvent> history);

    /**
     * 获取所有未提交的历史事件
     *
     * @return
     */
    List<DomainEvent> getChanges();

    /**
     * 变更已提交, 清理数据
     */
    void acceptChanges();

}
