package org.gollum.simple.storage;

import java.io.Serializable;

/**
 * 聚合根快照
 *
 * @param <K>
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public interface AggregateSnapshot<K> extends Serializable {

    /**
     * 获取聚合根ID
     *
     * @return
     */
    K getAggregateRootId();

}
