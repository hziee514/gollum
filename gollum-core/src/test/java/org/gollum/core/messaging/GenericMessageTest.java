package org.gollum.core.messaging;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
public class GenericMessageTest {

    @Test
    public void test_genericMessage() throws Exception {
        GenericMessage<Object> message1 = new GenericMessage<>(new Object());
        GenericMessage<Integer> message2 = new GenericMessage<>(1234);
        GenericMessage<String> message3 = new GenericMessage<>("asdf");

        Message<String> message4 = message3.withMetaData(MetaData.with("foo", "bar"));
    }

}