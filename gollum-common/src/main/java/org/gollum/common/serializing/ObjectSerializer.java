package org.gollum.common.serializing;

/**
 * @author wurenhai
 * @date 2018/1/17
 */
public interface ObjectSerializer {

    byte[] serialize(Object obj);

    Object deserialize(byte[] data);

}
