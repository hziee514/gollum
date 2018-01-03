package org.gollum.core.common;

import org.junit.Test;

/**
 * @author wurenhai
 * @date 2018/1/3
 */
public class AssertionTest {

    @Test(expected = IllegalArgumentException.class)
    public void notNull_withNull() throws Exception {
        Object obj = null;
        Assertion.notNull(obj, "object");
    }

    @Test
    public void notNull_ok() throws Exception {
        Object obj = new Object();
        Assertion.notNull(obj, "object");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_withNull() throws Exception {
        String str = null;
        Assertion.notNullOrEmpty(str, "string");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullOrEmpty_withEmpty() throws Exception {
        String str = "";
        Assertion.notNullOrEmpty(str, "string");
    }

    @Test
    public void notNullOrEmpty_ok() throws Exception {
        String str = "hello";
        Assertion.notNullOrEmpty(str, "string");
    }

    @Test(expected = IllegalArgumentException.class)
    public void negative_withZero() throws Exception {
        Assertion.negative(0, "number");
    }

    @Test(expected = IllegalArgumentException.class)
    public void negative_withPositive() throws Exception {
        Assertion.negative(1, "number");
    }

    @Test
    public void negative_ok() throws Exception {
        Assertion.negative(-1, "number");
    }

    @Test(expected = IllegalArgumentException.class)
    public void positive_withZero() throws Exception {
        Assertion.positive(0, "number");
    }

    @Test(expected = IllegalArgumentException.class)
    public void positive_withNegative() throws Exception {
        Assertion.positive(-1, "number");
    }

    @Test
    public void positive_ok() throws Exception {
        Assertion.positive(1, "number");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonNegative_withNegative() throws Exception {
        Assertion.nonNegative(-1, "number");
    }

    @Test
    public void nonNegative_ok() throws Exception {
        Assertion.nonNegative(0, "number");
        Assertion.nonNegative(1, "number");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonPositive_withPositive() throws Exception {
        Assertion.nonPositive(1, "number");
    }

    @Test
    public void nonPositive_ok() throws Exception {
        Assertion.nonPositive(0, "number");
        Assertion.nonPositive(-1, "number");
    }

}