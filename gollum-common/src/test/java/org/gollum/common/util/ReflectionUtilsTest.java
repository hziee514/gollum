package org.gollum.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wurenhai
 * @date 2018/1/15
 */
public class ReflectionUtilsTest {

    private static class A {
        private int a = 123456;
        private A () {}
    }

    private static class B<T> {
        private  T b;
        private B() {}

        public void setB(T b) {
            this.b = b;
        }

        public T getB() {
            return b;
        }
    }

    private static class C extends B<Integer> {
        private C(){}
    }

    @Test
    public void newInstance() throws Exception {
        A a = ReflectionUtils.newInstance(A.class);
        assertNotNull(a);
        B b = ReflectionUtils.newInstance(B.class);
        assertNotNull(b);
        C c = ReflectionUtils.newInstance(C.class);
        assertNotNull(c);
        b.setB(a);
        assertEquals(b.getB(), a);
    }
}