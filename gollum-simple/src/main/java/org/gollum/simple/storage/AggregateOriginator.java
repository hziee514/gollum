package org.gollum.simple.storage;

/**
 * 用于获取和保存聚合根最新快照, 实现聚合根的快照功能
 * 需要快照功能的聚合根必须实现此接口
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public interface AggregateOriginator {

    /**
     * 生成聚合根最新快照
     *
     * @return
     */
    AggregateSnapshot takeSnapshot();

    /**
     * 从快照中恢复聚合根状态
     *
     * @param snapshot
     */
    void restoreFromSnapshot(AggregateSnapshot snapshot);

}
