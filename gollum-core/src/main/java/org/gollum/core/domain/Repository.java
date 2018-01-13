package org.gollum.core.domain;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public interface Repository<T extends BaseAggregateRoot> {

    /**
     * 根据ID获取获取聚合根实例
     *
     * @param aggregateRootId
     * @return
     */
    T getById(String aggregateRootId);

    /**
     * 提交聚合根的变更
     *
     * @param aggregateRoot
     * @param expectedVersion
     */
    void commit(T aggregateRoot, int expectedVersion);

}
