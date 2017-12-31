package org.gollum.core.messaging;

import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
public class MetaData implements Map<String, Object>, Serializable {

    private static final String UNSUPPORTED_MUTATION_MSG = "Metadata is immutable";
    private static final MetaData EMPTY_META_DATA = new MetaData();

    private final Map<String, Object> values;

    public static MetaData emptyInstance() {
        return EMPTY_META_DATA;
    }

    public static MetaData from(Map<String, ?> entries) {
        if (entries instanceof MetaData) {
            return (MetaData) entries;
        } else if (entries == null || entries.isEmpty()) {
            return emptyInstance();
        } else {
            return new MetaData(entries);
        }
    }

    public static MetaData with(String key, Object value) {
        return new MetaData(Collections.singletonMap(key, value));
    }

    private MetaData() {
        values = Collections.emptyMap();
    }

    public MetaData(Map<String, ?> items) {
        values = Collections.unmodifiableMap(new HashMap<>(items));
    }

    public MetaData and(String key, Object value) {
        HashMap<String, Object> newValuse = new HashMap<>(values);
        newValuse.put(key, value);
        return new MetaData(newValuse);
    }

    public MetaData andIfNotPresent(String key, Supplier<Object> value) {
        return containsKey(key) ? this : this.and(key, value.get());
    }

    public MetaData mergedWith(Map<String, ?> entries) {
        if (entries.isEmpty()) {
            return this;
        }
        if (isEmpty()) {
            return new MetaData(entries);
        }
        Map<String, Object> merged = new HashMap<>(values);
        merged.putAll(entries);
        return new MetaData(merged);
    }

    public MetaData withoutKeys(Set<String> keys) {
        if (keys.isEmpty()) {
            return this;
        }
        Map<String, ?> modified = new HashMap<>(values);
        keys.forEach(modified::remove);
        return new MetaData(modified);
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return values.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return values.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        throw new UnsupportedOperationException(UNSUPPORTED_MUTATION_MSG);
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException(UNSUPPORTED_MUTATION_MSG);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        throw new UnsupportedOperationException(UNSUPPORTED_MUTATION_MSG);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException(UNSUPPORTED_MUTATION_MSG);
    }

    @Override
    public Set<String> keySet() {
        return values.keySet();
    }

    @Override
    public Collection<Object> values() {
        return values.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return values.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Map)) return false;
        return values.equals(o);
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }
}
