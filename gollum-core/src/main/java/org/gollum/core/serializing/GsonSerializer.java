package org.gollum.core.serializing;

import com.google.gson.Gson;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class GsonSerializer implements IJsonSerializer {

    private final ThreadLocal<Gson> gson = new ThreadLocal<Gson>() {
        @Override
        protected Gson initialValue() {
            return new Gson();
        }
    };

    @Override
    public String serialize(Object obj) {
        return gson.get().toJson(obj);
    }

    @Override
    public Object deserialize(String json, Class<?> type) {
        return gson.get().fromJson(json, type);
    }

}
