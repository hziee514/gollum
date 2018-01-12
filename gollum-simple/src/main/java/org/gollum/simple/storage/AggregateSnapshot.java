package org.gollum.simple.storage;

import java.io.Serializable;

/**
 * 聚合根快照
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public interface AggregateSnapshot extends Serializable {

    /**
     * 获取聚合根ID
     *
     * @return
     */
    long getAggregateRootId();

}
