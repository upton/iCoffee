package name.upton.zest;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UIDCreator {
    private static int HOST = getHost();
    private static int TIMESTAMP = getStartTimeStamp();
    private static AtomicInteger count = new AtomicInteger(0);
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        lock.writeLock().lock();
                        TIMESTAMP = TIMESTAMP + 1;
                        count = new AtomicInteger(0);
                    } finally {
                        lock.writeLock().unlock();
                    }
                }
            }
        }, "UIDCreatorTimeStampUpdateThread").start();
    }

    public static byte[] uid() {
        int t;
        int number;

        try {
            lock.readLock().lock();
            t = TIMESTAMP;
            number = count.incrementAndGet();
        } finally {
            lock.readLock().unlock();
        }

        byte[] b = new byte[12];
        ByteBuffer bb = ByteBuffer.wrap(b);
        
        bb.putInt(t);
        bb.putInt(HOST);
        bb.putInt(number);

        reverse(b);

        return b;

    }

    private static void reverse(byte[] b) {
        for (int i = 0; i < b.length / 2; i++) {
            byte t = b[i];
            b[i] = b[b.length - (i + 1)];
            b[b.length - (i + 1)] = t;
        }
    }

    private static int getHost() {
        int machinePiece;
        StringBuilder sb = new StringBuilder();

        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                sb.append(ni.toString());
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }

        machinePiece = sb.toString().hashCode();

        return machinePiece;
    }

    private static int getStartTimeStamp() {
        // 2010-01-01 00:00:00 000的毫秒数
        long start = 1262275200000L;

        return (int) ((new Date().getTime() - start) / 1000);
    }

    private static byte[] long2Bytes(long ll) {
        byte[] bb = new byte[8];
        bb[0] = (byte) (ll >> 56);
        bb[1] = (byte) (ll >> 48);
        bb[2] = (byte) (ll >> 40);
        bb[3] = (byte) (ll >> 32);
        bb[4] = (byte) (ll >> 24);
        bb[5] = (byte) (ll >> 16);
        bb[6] = (byte) (ll >> 8);
        bb[7] = (byte) (ll >> 0);

        return bb;
    }

    private static byte[] long2BytesR(long ll) {
        byte[] bb = new byte[8];
        bb[7] = (byte) (ll >> 56);
        bb[6] = (byte) (ll >> 48);
        bb[5] = (byte) (ll >> 40);
        bb[4] = (byte) (ll >> 32);
        bb[3] = (byte) (ll >> 24);
        bb[2] = (byte) (ll >> 16);
        bb[1] = (byte) (ll >> 8);
        bb[0] = (byte) (ll >> 0);

        return bb;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getStartTimeStamp());
        System.out.println(Integer.toHexString((123456 << 16) | (123143 & 0xFFFFFF)));
    }

    public static void test() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(uid());
                        cc.incrementAndGet();
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                long last = 0L;
                while (true) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    long now = cc.get();
                    System.out.println(now - last);
                    last = now;
                }
            }
        }).start();

    }

    private static AtomicLong cc = new AtomicLong();
}