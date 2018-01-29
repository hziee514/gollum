package org.gollum.simple.storage;

/**
 * 聚合根快照持久化接口
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public interface SnapshotStorage<K, T extends AggregateSnapshot<K>> {

    /**
     * 读取快照
     *
     * @param aggregateRootId
     * @return
     */
    T readSnapshot(K aggregateRootId);

    /**
     * 保存快照
     *
     * @param snapshot
     */
    void saveSnapshot(T snapshot);

}
