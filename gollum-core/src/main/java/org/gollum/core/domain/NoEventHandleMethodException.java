package org.gollum.core.domain;

import org.gollum.common.exception.GollumException;

/**
 * @author wurenhai
 * @date 2018/1/13
 */
public class NoEventHandleMethodException extends GollumException {
    public NoEventHandleMethodException(Class<?> aggregateRootType, Class<?> eventType, String message) {
        super(String.format("AggregateRoot[%s], EventType[%s]: %s",
                aggregateRootType.getName(), eventType.getName(), message));
    }
}
