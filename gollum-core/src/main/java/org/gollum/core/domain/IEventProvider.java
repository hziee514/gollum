package org.gollum.core.domain;

import org.gollum.core.eventing.DomainEvent;

import java.util.Collection;
import java.util.List;

/**
 * 提供聚合根状态恢复以及获取变更的接口
 * 每个聚合根都必须实现此接口
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public interface IEventProvider {

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
    Collection<DomainEvent> getUncommittedChanges();

}
