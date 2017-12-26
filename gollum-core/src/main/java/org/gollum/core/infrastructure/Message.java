package org.gollum.core.infrastructure;

import java.util.UUID;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class Message {

    private String id;

    private long timestamp;

    public Message() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
