package org.gollum.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author wurenhai
 * @date 2018/1/4
 */
public class BankAppTest {

    @Test
    public void test_reduce() throws Exception {

        int sum;

        sum = Stream.of(1)
                .reduce(0, (a, b) -> a+b);
        assertEquals(sum, 1);

        sum = new ArrayList<Integer>().stream()
                .reduce(0, (a, b) -> a+b);
        assertEquals(sum, 0);

        sum = Stream.of(1, 2, 3)
                .reduce(0, (a, b) -> a+b);
        assertEquals(sum, 6);

        sum = Stream.of(100, 200, 300)
                .reduce(0, (a, b) -> a+b);
        assertEquals(sum, 600);

    }

}