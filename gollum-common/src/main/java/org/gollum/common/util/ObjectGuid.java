package org.gollum.common.util;

import java.util.UUID;

/**
 * @author wurenhai
 * @date 2018/1/12
 */
public class ObjectGuid implements ObjectId<String> {

    @Override
    public String newId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public String newStringId(String prefix) {
        return prefix + newId();
    }

}
