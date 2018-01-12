package org.gollum.simple.domain;

/**
 * 聚合根仓储
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public interface Repository<T extends AggregateRoot> {

    /**
     * 根据ID获取聚合根实例
     *
     * @param aggregateRootId
     * @param type
     * @return
     */
    T getById(long aggregateRootId, Class<?> type);

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
