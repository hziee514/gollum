package org.gollum.core.messaging;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
public interface Message<T> extends Serializable {

    /**
     * identifier of the message
     *
     * @return
     */
    String getId();

    /**
     * timestamp of the message
     *
     * @return
     */
    long getTimestamp();

    /**
     * meta data of the message
     *
     * @return
     */
    MetaData getMetaData();

    /**
     * payload of the message
     *
     * @return
     */
    T getPayload();

    /**
     * type of the payload
     *
     * @return
     */
    Class<T> getPayloadType();

    /**
     * return a copy of the message with given metadata. keep payload unchanged
     *
     * @param metaData
     * @return
     */
    Message<T> withMetaData(Map<String, ?> metaData);

    /**
     * return a copy of the message with it metadata merged with given metadata, keep payload unchanged
     *
     * @param metaData
     * @return
     */
    Message<T> andMetaData(Map<String, ?> metaData);

}
