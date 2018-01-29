package org.gollum.common.domain;

import java.lang.reflect.Field;

/**
 * 值类型抽象
 *
 * @author wurenhai
 * @date 2017/12/26
 */
public abstract class ValueObject {

    private Object[] getAttributes() {
        Field[] fields = getClass().getDeclaredFields();
        if (fields.length == 0) {
            return new Object[]{};
        }
        try {
            Object[] result = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                result[i] = field.get(this);
            }
            return result;
        } catch (Exception e) {
            return new Object[]{};
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        for (Object obj : getAttributes()) {
            result = result * prime + (obj == null ? 0 : obj.hashCode());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            return hashCode() == obj.hashCode();
        }
        return false;
    }
}
