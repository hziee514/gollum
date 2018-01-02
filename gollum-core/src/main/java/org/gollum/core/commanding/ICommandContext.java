package org.gollum.core.commanding;

import org.gollum.core.domain.AggregateRoot;

/**
 * @author wurenhai
 * @date 2017/12/28
 */
@Deprecated
public interface ICommandContext {

    void add(AggregateRoot aggregateRoot);

    <T extends AggregateRoot> T get(String aggregateRootId, Class<T> type);

}
