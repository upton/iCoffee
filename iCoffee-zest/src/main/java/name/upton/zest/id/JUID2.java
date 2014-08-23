package name.upton.zest.id;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 无中心化的分布式全局唯一ID<br>
 * <br>
 * JUID长度为11个byte(转换成HEX字符串为22个字符)，由3段构成：<br>
 * 1、前面4个byte(8个HEX字符)存储的是从2010-01-01 00:00:00 000到现在的秒数;<br>
 * 2、中间4个byte(8个HEX字符)存储的是机器网卡的hash code + 进程号的hash code组合的编码;<br>
 * 3、最后3个byte(6个HEX字符)存储的是每一秒重置一次的计数器值;<br>
 * <br>
 * 因为JUID以时间戳开头的，所以它是近似有序的，如果使用JUID代替UUID做数据库主键，索引插入效率会高很多。<br>
 * 在同一个进程中每秒最多可以创建16,777,216个不同的JUID。
 * 
 * @author chenzehong
 * 
 */
public class JUID2 {
    // 2010-01-01 00:00:00 000的毫秒数
    private static final long _TIME_ZERO = 1262275200000L;
    private static final int _MAGIC_PRIME_NUM = 131;
    public static final int JUID_HEX_STR_LENGTH = 22;
    private static final ReentrantLock _LOCK = new ReentrantLock();
    
    private static int _INSTANCE;
    private static int _LAST_TIMESTAMP;
    private static int _COUNTER = 0;

    static {
        StringBuilder sb = new StringBuilder();

        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                sb.append(ni.toString());
            }
        } catch (IOException e) {
            throw new JUIDException("Init JUID error", e);
        }

        int machinePiece = Math.abs(sb.toString().hashCode()) << 16;
        int processPiece = Math.abs(java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode()) & 0xFFFF;

        _INSTANCE = machinePiece | processPiece;

        // timestamp相当于从2010-01-01 00:00:00 000 到现在的秒数
        _LAST_TIMESTAMP = (int) ((System.currentTimeMillis() - _TIME_ZERO) / 1000L);
    }

    private int timestamp;
    private int instance;
    private int counter;

    /**
     * 构建一个JUID对象
     * 
     * @param timestamp
     *            时间戳
     * @param instance
     *            主机+进程的组合
     * @param counter
     *            计数器值
     */
    private JUID2(int timestamp, int instance, int counter) {
        this.timestamp = timestamp;
        this.instance = instance;
        this.counter = counter;
    }

    /**
     * 创建一个新的JUID对象
     * 
     * @return JUID
     */
    public static JUID2 newJUID() {
        try {
            _LOCK.lock();

            int t = (int) ((System.currentTimeMillis() - _TIME_ZERO) / 1000L);
            
            if(t != _LAST_TIMESTAMP){
                _COUNTER = 0;
                
                if(t < _LAST_TIMESTAMP){
                    try {
                        throw new JUIDException("Clock was moved backwards at least " + ((_LAST_TIMESTAMP - t)) + " seconds. So, will update _INSTANCE code.");
                    } catch (JUIDException e) {
                        e.printStackTrace();
                    }
                    
                    // 当时间被倒退了，重新修正_INSTANCE
                    _INSTANCE = Math.abs(_INSTANCE * _MAGIC_PRIME_NUM);
                }
            }
            
            int c = ++_COUNTER;
            _LAST_TIMESTAMP = t;
            
            return new JUID2(t, _INSTANCE, c);
        } finally {
            _LOCK.unlock();
        }
    }

    /**
     * 从JUID的16进制字符串转换成JUID
     * 
     * @param juidHexStr
     *            JUID字符串
     * @return JUID
     */
    public static JUID2 fromHexStr(String juidHexStr) {
        if (juidHexStr == null) {
            throw new IllegalArgumentException("juid hex string must not null");
        }

        if (juidHexStr.length() != JUID_HEX_STR_LENGTH) {
            throw new IllegalArgumentException("juid hex string's length must be " + JUID_HEX_STR_LENGTH);
        }

        try {
            byte[] bb = new byte[JUID_HEX_STR_LENGTH / 2];

            for (int i = 0; i < bb.length; i++) {
                bb[i] = (byte) Integer.parseInt(juidHexStr.substring(i * 2, i * 2 + 2), 16);
            }

            return fromBytes(bb);
        } catch (NumberFormatException e) {
            throw new JUIDException("Format JUID hex string error", e);
        }
    }
    
    public static JUID2 fromBytes(byte[] bb) {
        if (bb == null) {
            throw new IllegalArgumentException("bytes must not null");
        }

        if (bb.length != (JUID_HEX_STR_LENGTH / 2)) {
            throw new IllegalArgumentException("bytes length must be " + (JUID_HEX_STR_LENGTH / 2));
        }

        try {
            int t = (int) ((((bb[0] & 0xff) << 24) | ((bb[1] & 0xff) << 16) | ((bb[2] & 0xff) << 8) | ((bb[3] & 0xff) << 0)));
            int i = (int) ((((bb[4] & 0xff) << 24) | ((bb[5] & 0xff) << 16) | ((bb[6] & 0xff) << 8) | ((bb[7] & 0xff) << 0)));
            int c = (int) (((((byte)0 & 0xff) << 24) | ((bb[8] & 0xff) << 16) | ((bb[9] & 0xff) << 8) | ((bb[10] & 0xff) << 0)));

            return new JUID2(t, i, c);
        } catch (NumberFormatException e) {
            throw new JUIDException("Format bytes error", e);
        }
    }
    
    public static byte[] convertHexStr2Bytes(String juidHexStr) {
        if (juidHexStr == null) {
            throw new IllegalArgumentException("juid hex string must not null");
        }

        if (juidHexStr.length() != JUID_HEX_STR_LENGTH) {
            throw new IllegalArgumentException("juid hex string's length must be " + JUID_HEX_STR_LENGTH);
        }

        try {
            byte[] bb = new byte[JUID_HEX_STR_LENGTH / 2];

            for (int i = 0; i < bb.length; i++) {
                bb[i] = (byte) Integer.parseInt(juidHexStr.substring(i * 2, i * 2 + 2), 16);
            }

            return bb;
        } catch (NumberFormatException e) {
            throw new JUIDException("Format JUID hex string error", e);
        }
    }

    /**
     * 输出JUID的byte数组
     * 
     * @return JUID的byte数组
     */
    public byte[] toBytes() {
        byte[] b = new byte[11];

        b[0] = (byte) (timestamp >> 24);
        b[1] = (byte) (timestamp >> 16);
        b[2] = (byte) (timestamp >> 8);
        b[3] = (byte) (timestamp >> 0);
        
        b[4] = (byte) (instance >> 24);
        b[5] = (byte) (instance >> 16);
        b[6] = (byte) (instance >> 8);
        b[7] = (byte) (instance >> 0);

        // 只输出counter低位上的3个byte，因为设计中最高位的一个byte是0，不需要转换
        b[8] = (byte) (counter >> 16);
        b[9] = (byte) (counter >> 8);
        b[10] = (byte) (counter >> 0);

        return b;
    }

    /**
     * 输出JUID 16进制的等宽字符串
     */
    @Override
    public String toString() {
        return toString(true);
    }
    
    /**
     * 输出JUID 16进制的字符串
     * @param 是否输出等宽字符串
     */
    public String toString(boolean monospaced) {
        byte[] b = toBytes();
        StringBuilder buf = new StringBuilder(JUID_HEX_STR_LENGTH);

        for (int i = 0; i < b.length; i++) {
            int x = b[i] & 0xFF;
            
            if(!monospaced && (i == 8 || i == 9) && x == 0){
                // 对计数值的前两位进行判断，看是否为0，并进行省略
                // ignore
            } else {
                String s = Integer.toHexString(x);

                if (s.length() == 1) {
                    buf.append("0");
                }

                buf.append(s);
            }
        }

        return buf.toString();
    }

    /**
     * 转成JUID原始的存储内容字符串，方便调试
     * 
     * @return 转成JUID原始的存储内容字符串
     */
    public String rawString() {
        return "[timestamp=" + timestamp + ", instance=" + instance + ", counter=" + counter + "]";
    }
}

/**
 * JUID异常
 * 
 * @author chenzehong
 * 
 */
class JUIDException extends RuntimeException {
    private static final long serialVersionUID = -8151413230773546638L;

    public JUIDException(String message) {
        super(message);
    }
    
    public JUIDException(String message, Throwable cause) {
        super(message, cause);
    }
}