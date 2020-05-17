package com.family.fmlbase.utils;

import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.UUID;

public class DistribID {
    // 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
    private final long twepoch = 1288834974657L;
    // 机器标识位数
    private final long workerIdBits = 5L;
    // 数据中心标识位数
    private final long dataCenterIdBits = 5L;
    // 机器ID最大值
    private final long maxWorkerId = 31L;
    // 数据中心ID最大值
    private final long maxDataCenterId = 31L;
    // 毫秒内自增位
    private final long sequenceBits = 12L;
    // 机器ID偏左移12位
    private final long workerIdShift = 12L;
    // 数据中心ID左移17位
    private final long dataCenterIdShift = 17L;
    // 时间毫秒左移22位
    private final long timestampLeftShift = 22L;

    private final long sequenceMask = 4095L;

    private long workerId;
    // 数据标识id部分
    private long dataCenterId;
    // 0，并发控制
    private long sequence = 0L;
    /* 上次生产id时间戳 */
    private long lastTimestamp = -1L;

    public DistribID() {
        this.dataCenterId = getDataCenterId(31L);
        this.workerId = getWorkerId(this.dataCenterId, 31L);
    }

    public DistribID(long workerId, long datacenterId) {
        if (workerId > 31L || workerId < 0L)
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", new Object[] { Long.valueOf(31L) }));
        if (datacenterId > 31L || datacenterId < 0L)
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", new Object[] { Long.valueOf(31L) }));
        this.workerId = workerId;
        this.dataCenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < this.lastTimestamp)
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", new Object[] { Long.valueOf(this.lastTimestamp - timestamp) }));
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1L & 0xFFFL;
            if (this.sequence == 0L)
                timestamp = tilNextMillis(this.lastTimestamp);
        } else {
            this.sequence = 0L;
        }
        this.lastTimestamp = timestamp;
        return timestamp - 1288834974657L << 22L | this.dataCenterId << 17L | this.workerId << 12L | this.sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp)
            timestamp = timeGen();
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    protected static long getWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!StringUtils.isEmpty(name))
            mpid.append(name.split("@")[0]);
        return (mpid.toString().hashCode() & 0xFFFF) % (maxWorkerId + 1L);
    }

    protected static long getDataCenterId(long maxDataCenterId) {
        long id = 0L;
        try {
            byte[] mac = null;
            Enumeration<NetworkInterface> subInterfaces = NetworkInterface.getNetworkInterfaces();
            while (subInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = subInterfaces.nextElement();
                if (networkInterface.getHardwareAddress() != null) {
                    mac = networkInterface.getHardwareAddress();
                    break;
                }
            }
            if (mac == null)
                mac = UUID.randomUUID().toString().getBytes();
            id = (0xFFL & mac[mac.length - 1] | 0xFF00L & mac[mac.length - 2] << 8L) >> 6L;
            id %= maxDataCenterId + 1L;
        } catch (Exception e) {
            LoggerFactory.getLogger(DistribID.class).error("get_data_center_id", e);
        }
        return id;
    }

    public static void main(String[] args) {

        DistribID distribID =new DistribID(1L,2L);
        for (int i = 0; i < 10; i++) {
            System.out.println(distribID.nextId()+"");
        }
        System.out.println("##############3");
        DistribID distribID2 =new DistribID(2L,2L);
        for (int i = 0; i < 10; i++) {
            System.out.println(distribID2.nextId()+"");
        }

    }
}
