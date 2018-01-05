package org.gollum.core.domain;

/**
 * 根据聚合根类型获取Repository实例
 *
 * @author wurenhai
 * @date 2018/1/5
 */
public interface RepositoryProvider {

    <T extends BaseAggregateRoot> Repository<T> provide(Class<T> type);

}
