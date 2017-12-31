package org.gollum.core.messaging;

import java.util.Map;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
public abstract class AbstractMessage<T> implements Message<T> {

    private final String id;
    private final long timestamp;

    public AbstractMessage(String id) {
        this(id, System.currentTimeMillis());
    }

    public AbstractMessage(String id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public Message<T> withMetaData(Map<String, ?> metaData) {
        return null;
    }

    @Override
    public Message<T> andMetaData(Map<String, ?> metaData) {
        return null;
    }

    protected abstract Message<T> withMetaData(MetaData metaData);

}
