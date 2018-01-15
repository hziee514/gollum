package org.gollum.simple.domain;

import org.gollum.common.exception.NoDefaultConstructorException;
import org.gollum.common.util.Assertion;
import org.gollum.common.util.ReflectionUtils;
import org.gollum.simple.storage.AggregateSnapshot;
import org.gollum.simple.storage.SnapshotStorage;

/**
 * 抽象仓储类型，提供基础的仓储服务
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public abstract class BaseRepository<T extends BaseAggregateRoot> implements Repository<T> {

    private SnapshotStorage storage;

    /**
     * 注入存储实现(有些框架不能从构造函数注入)
     *
     * @param storage
     */
    protected void injectStorage(SnapshotStorage storage) {
        this.storage = storage;
    }

    /*public BaseRepository(SnapshotStorage storage) {
        this.storage = storage;
    }*/

    /**
     * 根据ID查询聚合根
     *
     * @param aggregateRootId 聚合根ID
     * @return 聚合根实例,不存在则返回null
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getById(long aggregateRootId) {
        AggregateSnapshot snapshot = storage.readSnapshot(aggregateRootId);
        if (snapshot == null) {
            return null;
        }

        Class<T> aggregateRootType = ReflectionUtils.getActualType(getClass());
        Assertion.notNull(aggregateRootType, "AggregateRootType");
        try {
            T instance = ReflectionUtils.newInstance(aggregateRootType);
            instance.restoreFromSnapshot(snapshot);
            return instance;
        } catch (ReflectiveOperationException e) {
            throw new NoDefaultConstructorException(aggregateRootType.getName());
        }
    }

    /**
     * 保存变更使用事物包裹
     *
     * @param aggregateRoot 聚合根实例
     * @param expectedVersion 期望版本号
     */
    @SuppressWarnings("unchecked")
    @Override
    public void commit(T aggregateRoot, int expectedVersion) {
        storage.saveSnapshot(aggregateRoot.takeSnapshot());
    }

}
