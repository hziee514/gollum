package org.gollum.common.serializing;

import java.io.*;

/**
 * @author wurenhai
 * @date 2018/1/17
 */
public class JavaSerializer implements ObjectSerializer {

    @Override
    public byte[] serialize(Object obj) {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] data) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
