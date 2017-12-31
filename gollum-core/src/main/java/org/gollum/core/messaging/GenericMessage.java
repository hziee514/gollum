package org.gollum.core.messaging;

import java.util.Map;
import java.util.UUID;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
public class GenericMessage<T> extends AbstractMessage<T> {

    private final MetaData metaData;
    private final T payload;

    public GenericMessage(T payload) {
        this(UUID.randomUUID().toString(), payload);
    }

    public GenericMessage(String id, T payload) {
        this(id, payload, MetaData.emptyInstance());
    }

    public GenericMessage(T payload, Map<String, ?> metaData) {
        this(UUID.randomUUID().toString(), payload, metaData);
    }

    public GenericMessage(String id, T payload, Map<String, ?> metaData) {
        super(id);
        this.payload = payload;
        this.metaData = MetaData.from(metaData);
    }

    public GenericMessage(GenericMessage<T> original, MetaData metaData) {
        super(original.getId(), original.getTimestamp());
        this.payload = original.getPayload();
        this.metaData = metaData;
    }

    @Override
    public MetaData getMetaData() {
        return metaData;
    }

    @Override
    public T getPayload() {
        return payload;
    }

    @Override
    public Class<T> getPayloadType() {
        return (Class<T>)payload.getClass();
    }

    @Override
    protected Message<T> withMetaData(MetaData metaData) {
        return new GenericMessage<>(this, metaData);
    }

}
