package org.gollum.core.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author wurenhai
 * @date 2017/12/26
 */
public class ValueObjectTest {

    static class AObject extends ValueObject {

        private int a;

        public AObject(int a) {
            this.a = a;
        }

        protected Object[] getAttributes() {
            return new Object[] { a };
        }
    }

    static class BObject extends ValueObject {

        private String b;

        public BObject(String b) {
            this.b = b;
        }

        protected Object[] getAttributes() {
            return new Object[] { b };
        }
    }

    static class CObject extends ValueObject {

        private AObject obj;

        private long c;

        public CObject(AObject obj, long c) {
            this.obj = obj;
            this.c = c;
        }

        protected Object[] getAttributes() {
            return new Object[] { obj, c };
        }
    }

    @Test
    public void test_equals() throws Exception {
        AObject a1 = new AObject(1);
        AObject a2 = new AObject(1);
        AObject a3 = new AObject(12345678);
        AObject a4 = new AObject(12345678);
        assertEquals(a1, a2);
        assertEquals(a3, a4);
        assertNotEquals(a1, a3);

        BObject b1 = new BObject("hello");
        BObject b2 = new BObject("hello");
        BObject b3 = new BObject("中国");
        BObject b4 = new BObject("中国");
        assertEquals(b1, b2);
        assertEquals(b3, b4);
        assertNotEquals(b1, b3);

        CObject c1 = new CObject(a3, -1L);
        CObject c2 = new CObject(a3, -1L);
        CObject c3 = new CObject(a1, -123456789L);
        CObject c4 = new CObject(a1, -123456789L);
        assertEquals(c1, c2);
        assertEquals(c3, c4);
        assertNotEquals(c1, c3);
    }

}