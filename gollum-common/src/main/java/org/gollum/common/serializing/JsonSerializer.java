package org.gollum.common.serializing;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface JsonSerializer {

    String serialize(Object obj);

    Object deserialize(String json, Class<?> type);

}
