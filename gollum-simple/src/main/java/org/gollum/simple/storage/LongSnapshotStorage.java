package org.gollum.simple.storage;

/**
 * @author wurenhai
 * @date 2018/1/29
 */
public interface LongSnapshotStorage<T extends LongAggregateSnapshot> extends SnapshotStorage<Long, T> {
}
