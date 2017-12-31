package org.gollum.core.messaging;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wurenhai
 * @date 2017/12/31
 */
public class MetaDataTest {

    @Test
    public void test_metaData() throws Exception {
        MetaData metaData1 = MetaData.with("nullkey", null);
        MetaData metaData2 = metaData1.and("otherkey", "value");
        MetaData metaData3 = metaData2.and("lastkey", "lastvalue");

        assertEquals(1, metaData1.size());
        assertEquals(2, metaData2.size());
        assertEquals(3, metaData3.size());

        assertNotEquals(metaData1, metaData2);
        assertNotEquals(metaData2, metaData3);

        MetaData metaData4 = new MetaData(metaData3);
        assertEquals(metaData3, metaData4);
    }

}