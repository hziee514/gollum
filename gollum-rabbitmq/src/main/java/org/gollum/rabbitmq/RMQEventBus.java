package org.gollum.rabbitmq;

import org.gollum.common.serializing.ObjectSerializer;
import org.gollum.core.eventing.DomainEvent;
import org.gollum.core.eventing.EventBus;

import java.io.IOException;

/**
 * @author wurenhai
 * @date 2018/1/28
 */
public class RMQEventBus extends BaseClient implements EventBus {

    private String topic;
    private ObjectSerializer serializer;

    public RMQEventBus(ObjectSerializer serializer) {
        this.serializer = serializer;
    }

    public void init(String uri, String topic) throws Exception {
        init(uri);
        this.topic = topic;
        channel.exchangeDeclare(topic, "fanout", true);
    }

    @Override
    public void publish(DomainEvent event) {
        try {
            channel.basicPublish(topic, "", null, serializer.serialize(event));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
