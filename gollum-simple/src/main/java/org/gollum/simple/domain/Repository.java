package org.gollum.simple.domain;

/**
 * 聚合根仓储
 *
 * @param <T>
 * @param <K>
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public interface Repository<T extends AggregateRoot<K>, K> {

    /**
     * 根据ID获取聚合根实例
     *
     * @param aggregateRootId
     * @return
     */
    T getById(K aggregateRootId);

    /**
     * 提交聚合根的变更
     *
     * @param aggregateRoot
     */
    default void commit(T aggregateRoot) {
        commit(aggregateRoot, -1);
    }

    /**
     * 提交聚合根的变更
     *
     * @param aggregateRoot
     * @param expectedVersion
     */
    void commit(T aggregateRoot, int expectedVersion);

}
