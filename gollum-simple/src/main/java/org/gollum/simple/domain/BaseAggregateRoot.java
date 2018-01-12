package org.gollum.simple.domain;

import org.gollum.common.util.Assertion;
import org.gollum.simple.storage.AggregateOriginator;

/**
 * 聚合根抽象类, 所有聚合根都必须从这里继承
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public abstract class BaseAggregateRoot implements AggregateRoot, AggregateOriginator {

    protected long id;

    protected BaseAggregateRoot() {

    }

    protected BaseAggregateRoot(long id) {
        Assertion.positive(id, "id");
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

}
