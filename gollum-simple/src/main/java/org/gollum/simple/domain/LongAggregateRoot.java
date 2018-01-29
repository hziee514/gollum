package org.gollum.simple.domain;

import org.gollum.common.util.Assertion;
import org.gollum.simple.storage.AggregateOriginator;
import org.gollum.simple.storage.AggregateSnapshot;

/**
 * 聚合根抽象类, 所有聚合根都必须从这里继承
 *
 * @param <S>
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public abstract class LongAggregateRoot<S extends AggregateSnapshot> implements AggregateRoot<Long>, AggregateOriginator<S> {

    protected long id;

    protected LongAggregateRoot() {

    }

    protected LongAggregateRoot(long id) {
        Assertion.positive(id, "id");
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

}
