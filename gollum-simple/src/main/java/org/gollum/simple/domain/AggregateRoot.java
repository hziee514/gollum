package org.gollum.simple.domain;

/**
 * 聚合根抽象接口
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public interface AggregateRoot {

    /**
     * 获取聚合根ID
     *
     * @return
     */
    long getId();

}
