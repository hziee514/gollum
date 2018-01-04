package org.gollum.core.common;

import java.util.Random;

/**
 * 基于Snowflake算法的ID生成器
 *
 * @author wurenhai
 * @date 2017/12/4
 */
public class ObjectId {

    private static final long EPOCH = 1264953600000L;

    private static final int BITS_F0 = 10;
    private static final int BITS_F1 = 5;
    private static final int BITS_F2 = 5;

    //private static final int SHIFT_F0 = 0;
    private static final int SHIFT_F1 = BITS_F0;
    private static final int SHIFT_F2 = (BITS_F0 + BITS_F1);
    private static final int SHIFT_F3 = (BITS_F0 + BITS_F1 + BITS_F2);

    private static final int MASK_F0 = (1 << BITS_F0) - 1;
    private static final int MASK_F1 = (1 << BITS_F1) - 1;
    private static final int MASK_F2 = (1 << BITS_F2) - 1;

    /**
     * 机器ID
     */
    private long machine;

    /**
     * 进程ID
     */
    private long pid;

    /**
     * 序列
     */
    private long sequence;

    /**
     * 最新时间戳
     */
    private long latestTs;

    public ObjectId() {
        this(new Random().nextLong(), new Random().nextLong());
    }

    public ObjectId(long machine, long pid ) {
        this.machine = (machine & MASK_F2) << SHIFT_F2;
        this.pid = (pid & MASK_F1) << SHIFT_F1;
        this.latestTs = -1;
    }

    public String newStringId(String prefix) {
        return prefix + next();
    }

    public String newStringId() {
        return newStringId("");
    }

    public synchronized long next() {
        long timestamp = currentMs();
        if (timestamp < latestTs) {
            throw new RuntimeException("older timestamp");
        }
        if (latestTs == timestamp) {
            sequence = (sequence + 1) & MASK_F0;
            if (sequence == 0) {
                timestamp = nextMs(latestTs);
            }
        } else {
            sequence = 0;
        }
        long id = ((timestamp - EPOCH) << SHIFT_F3) | machine | pid | sequence;
        latestTs = timestamp;
        return id;
    }

    private long currentMs() {
        return System.currentTimeMillis();
    }

    private long nextMs(long latestTs) {
        long timestamp = currentMs();
        while (timestamp <= latestTs) {
            timestamp = currentMs();
        }
        return timestamp;
    }

}
