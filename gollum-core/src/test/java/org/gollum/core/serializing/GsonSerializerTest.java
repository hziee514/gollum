package org.gollum.core.serializing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class GsonSerializerTest {

    static class AObject {

        private String attr;

        public AObject(String attr) {
            this.attr = attr;
        }

        public String getAttr() {
            return attr;
        }
    }

    static class BObject {

        private AObject a;

        private int b;

        private long c;

        public BObject(AObject a, int b) {
            this.a = a;
            this.b = b;
            this.c = System.currentTimeMillis();
        }

        public AObject getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public long getC() {
            return c;
        }
    }

    @Test
    public void test_gsonSerializer() throws Exception {
        IJsonSerializer serializer = new GsonSerializer();
        AObject a = new AObject("hello");
        BObject raw = new BObject(a, 111);
        String json = serializer.serialize(raw);
        System.out.println(json);
        BObject bean = (BObject)serializer.deserialize(json, BObject.class);
        assertEquals(raw.getA().getAttr(), bean.getA().getAttr());
        assertEquals(raw.getB(), bean.getB());
        assertEquals(raw.getC(), bean.getC());
    }

}