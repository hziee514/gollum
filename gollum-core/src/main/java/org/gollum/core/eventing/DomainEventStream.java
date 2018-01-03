package org.gollum.core.eventing;

import java.util.List;

/**
 * 执行单个命令产生的事件打包发布
 *
 * @author wurenhai
 * @date 2018/1/3
 */
public class DomainEventStream {

    public DomainEventStream(String commandId,
                             String aggregateRootId,
                             String aggregateRootTypeName,
                             int aggregateRootVersion,
                             long timestamp,
                             List<DomainEvent> events) {

    }

}
