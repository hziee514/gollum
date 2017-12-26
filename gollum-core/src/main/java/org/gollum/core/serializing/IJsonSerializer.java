package org.gollum.core.serializing;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public interface IJsonSerializer {

    String serialize(Object obj);

    Object deserialize(String json, Class<?> type);

}
