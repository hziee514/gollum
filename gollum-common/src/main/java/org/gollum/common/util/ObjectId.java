package org.gollum.common.util;

/**
 * @author wurenhai
 * @date 2018/1/12
 */
public interface ObjectId<T> {

    T newId();

    default String newStringId() {
        return newStringId("");
    }

    String newStringId(String prefix);

}
