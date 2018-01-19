package org.gollum.common.serializing;

import org.gollum.common.domain.ValueObject;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

/**
 * @author wurenhai
 * @date 2018/1/17
 */
public class JavaSerializerTest {

    static class FieldObject extends ValueObject implements Serializable {
        private static final long serialVersionUID = -2617073459863860503L;

        String a;
        Integer i;

        @Override
        protected Object[] getAttributes() {
            return new Object[] {a, i};
        }
    }

    public static class TestObject implements Serializable {
        private static final long serialVersionUID = -6211418002406418029L;
        String s;
        int i;
        Long l;
        float f;
        FieldObject o;
    }

    public static class TestMessage<T> implements Serializable {
        private static final long serialVersionUID = 1643165036864584531L;
        String id;
        T payload;
        TestMessage(String id, T payload) {
            this.id = id;
            this.payload = payload;
        }

        String getId() {
            return id;
        }

        T getPayload() {
            return payload;
        }
    }

    @Test
    public void serialize_deserialize_general() {
        ObjectSerializer serializer = new JavaSerializer();
        FieldObject fo = new FieldObject();
        fo.a = "中文";
        TestMessage<FieldObject> tm = new TestMessage<>("123", fo);
        byte[] data = serializer.serialize(tm);
        assertNotNull(data);
        Object so = serializer.deserialize(data);
        assertNotNull(so);
        assertTrue(so instanceof TestMessage);
        TestMessage<?> oo = (TestMessage<?>)so;
        assertEquals(oo.getId(), "123");
        System.out.println(oo.getPayload().getClass().getName());
        assertTrue(oo.getPayload() instanceof FieldObject);
        assertEquals(oo.getPayload(), fo);
    }

    @Test
    public void serialize_deserialize_pass() {
        ObjectSerializer serializer = new JavaSerializer();
        FieldObject fo = new FieldObject();
        fo.a = "中文";
        TestObject to = new TestObject();
        to.s = "hello";
        to.i = 123;
        to.l = 123456789L;
        to.f = 0.12345F;
        to.o = fo;
        byte[] data = serializer.serialize(to);
        assertNotNull(data);
        System.out.println(data);
        Object so = serializer.deserialize(data);
        assertNotNull(so);
        assertTrue(so instanceof TestObject);
        TestObject oo = (TestObject)so;
        assertEquals(oo.s, to.s);
        assertEquals(oo.i, to.i);
        assertEquals(oo.l, to.l);
        assertEquals(oo.f, to.f, 0.00001);
        assertEquals(oo.o, to.o);
    }

}