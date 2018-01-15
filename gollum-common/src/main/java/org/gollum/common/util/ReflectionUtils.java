package org.gollum.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
public class ReflectionUtils {

    public static <T> T newInstance(Class<T> type) throws ReflectiveOperationException {
        Constructor<T> constructor = type.getDeclaredConstructor(new Class<?>[]{});
        constructor.setAccessible(true);
        return constructor.newInstance(new Object[]{});
    }

    public static <T> Class<T> getActualType(Class<?> type) {
        return getActualType(type, 0);
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getActualType(Class<?> type, int index) {
        if (!(type.getGenericSuperclass() instanceof ParameterizedType)) {
            return null;
        }
        return (Class<T>)((ParameterizedType)type.getGenericSuperclass()).getActualTypeArguments()[index];
    }

}
