package org.gollum.core.commanding;

import org.gollum.core.messaging.GenericMessage;
import org.gollum.core.messaging.Message;
import org.gollum.core.messaging.MessageDecorator;
import org.gollum.core.messaging.MetaData;

import java.util.Map;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
@Deprecated
public class GenericCommandMessage<T> extends MessageDecorator<T> implements CommandMessage<T> {

    public static <C> CommandMessage<C> asCommandMessage(Object command) {
        if (CommandMessage.class.isInstance(command)) {
            return (CommandMessage<C>)command;
        }
        return new GenericCommandMessage<>((C)command, MetaData.emptyInstance());
    }

    public GenericCommandMessage(T payload) {
        this(payload, MetaData.emptyInstance());
    }

    public GenericCommandMessage(T payload, Map<String, ?> metaData) {
        this(new GenericMessage<>(payload, metaData));
    }

    public GenericCommandMessage(Message<T> delegate) {
        super(delegate);
    }

    @Override
    public GenericCommandMessage<T> withMetaData(Map<String, ?> metaData) {
        return new GenericCommandMessage<>(getDelegate().withMetaData(metaData));
    }

    @Override
    public GenericCommandMessage<T> andMetaData(Map<String, ?> metaData) {
        return new GenericCommandMessage<>(getDelegate().andMetaData(metaData));
    }
}
