package org.gollum.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.gollum.common.serializing.ObjectSerializer;
import org.gollum.common.util.Assertion;
import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.EventConsumer;
import org.gollum.core.eventing.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wurenhai
 * @date 2018/1/28
 */
public class RMQEventConsumer extends BaseClient implements EventConsumer {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final Map<Class<? extends DomainEvent>, Set<EventHandler<? extends DomainEvent>>> handlers = new HashMap<>();

    private String topic;
    private String queueName;
    private ObjectSerializer serializer;

    public RMQEventConsumer(ObjectSerializer serializer) {
        this.serializer = serializer;
    }

    public void init(String uri, String topic) throws Exception {
        init(uri);
        this.topic = topic;
        channel.exchangeDeclare(topic, "fanout", true);
        queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, topic, "");
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                Object message = serializer.deserialize(body);
                if (message != null && message instanceof DomainEvent) {
                    consume((DomainEvent)message);
                }
            }
        });
    }

    @Override
    public void register(Class<? extends DomainEvent> type, EventHandler<? extends DomainEvent> handler) {
        Assertion.notNull(handler, "handler");
        Set set = findHandlersFor(type);
        set.add(handler);
    }

    @Override
    public <T extends DomainEvent> void consume(T e) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("consume: {}", e);
        }
        for (EventHandler handler : findHandlersFor(e.getClass())) {
            handler.handle(e);
        }
    }

    private Set<EventHandler<? extends DomainEvent>> findHandlersFor(Class<? extends DomainEvent> type) {
        if (handlers.containsKey(type)) {
            return handlers.get(type);
        } else {
            Set<EventHandler<? extends DomainEvent>> set = new HashSet<>();
            handlers.put(type, set);
            return set;
        }
    }

}
