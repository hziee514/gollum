package org.gollum.core.commanding;

import org.gollum.core.messaging.Message;

import java.util.Map;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
public interface CommandMessage<T> extends Message<T> {

    @Override
    CommandMessage<T> withMetaData(Map<String, ?> metaData);

    @Override
    CommandMessage<T> andMetaData(Map<String, ?> metaData);

}
