package org.gollum.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.gollum.core.commanding.Command;
import org.gollum.core.commanding.CommandBus;

import java.util.UUID;
import java.util.concurrent.Future;

/**
 * @author wurenhai
 * @date 2018/1/17
 */
public class RabbitmqCommandBus implements CommandBus {

    private Connection connection;
    private Channel channel;

    private String requestQueueName;
    private String replayQueueName;

    /**
     *
     * @param uri amqp://userName:password@hostName:portNumber/virtualHost
     */
    public RabbitmqCommandBus(String uri, String queueName) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);
        connection = factory.newConnection();
        channel = connection.createChannel();

        this.requestQueueName = queueName;
        replayQueueName = channel.queueDeclare().getQueue();
    }

    public void release() throws Exception {
        channel.close();
        connection.close();
    }

    @Override
    public Future<?> send(Command command) {
        String correlationId = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(correlationId)
                .replyTo(replayQueueName)
                .build();
        //channel.basicPublish("", requestQueueName, props, );
        return null;
    }

}
