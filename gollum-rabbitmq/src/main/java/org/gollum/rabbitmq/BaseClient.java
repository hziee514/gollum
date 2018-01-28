package org.gollum.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author wurenhai
 * @date 2018/1/28
 */
public abstract class BaseClient {

    protected Connection connection;
    protected Channel channel;

    protected void init(String uri) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    protected void close() throws Exception {
        channel.close();
        connection.close();
    }

}
