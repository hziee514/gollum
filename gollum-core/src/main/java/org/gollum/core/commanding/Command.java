package org.gollum.core.commanding;

import java.io.Serializable;

/**
 * @author wurenhai
 * @date 2018/1/2
 */
public interface Command extends Serializable {

    /**
     * Id of this command
     * 用于在处理端实现幂等操作
     *
     * @return
     */
    String getId();

    /**
     * 获取聚合根ID
     *
     * @return
     */
    String getAggregateRootId();

    /**
     * 获取聚合根版本号
     * 用于检测版本冲突
     *
     * @return
     */
    int getAggregateRootVersion();

}
