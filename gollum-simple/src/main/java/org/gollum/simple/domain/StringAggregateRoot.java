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
public abstract class StringAggregateRoot<S extends AggregateSnapshot> implements AggregateRoot<String>, AggregateOriginator<S> {

    protected String id;

    protected StringAggregateRoot() {

    }

    protected StringAggregateRoot(String id) {
        Assertion.notNullOrEmpty(id, "id");
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

}
