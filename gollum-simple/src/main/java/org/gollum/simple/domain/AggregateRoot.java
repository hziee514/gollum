package org.gollum.simple.domain;

/**
 * 聚合根抽象接口
 *
 * @param <K>
 *
 * @author wurenhai
 * @date 2018/1/10
 */
public interface AggregateRoot<K> {

    /**
     * 获取聚合根ID
     *
     * @return
     */
    K getId();

}
