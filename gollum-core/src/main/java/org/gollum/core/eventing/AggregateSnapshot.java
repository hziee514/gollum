package org.gollum.core.eventing;

import java.io.Serializable;

/**
 * 聚合根快照
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public interface AggregateSnapshot extends Serializable {

    /**
     * 获取聚合根ID
     *
     * @return
     */
    String getAggregateRootId();

    /**
     * 获取聚合根版本号
     *
     * @return
     */
    int getVersion();

    /**
     * 获取时间戳
     * @return
     */
    @Deprecated
    long getTimestamp();

}
