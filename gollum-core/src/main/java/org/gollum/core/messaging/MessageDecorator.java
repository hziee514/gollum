package org.gollum.core.messaging;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
@Deprecated
public abstract class MessageDecorator<T> implements Message<T> {

    private final Message<T> delegate;

    protected MessageDecorator(Message<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public long getTimestamp() {
        return delegate.getTimestamp();
    }

    @Override
    public MetaData getMetaData() {
        return delegate.getMetaData();
    }

    @Override
    public T getPayload() {
        return delegate.getPayload();
    }

    @Override
    public Class<T> getPayloadType() {
        return delegate.getPayloadType();
    }

    protected Message<T> getDelegate() {
        return delegate;
    }

}
