package name.upton.zest.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class WriterVsRaf {

    public static void main(String[] args) throws IOException {
        int num = 2000000;
        writer(num);
        raf(num);
    }

    public static void raf(final int num) throws IOException{
        long start = System.currentTimeMillis();
        
        RandomAccessFile raf = new RandomAccessFile("d:\\1.txt", "rw");
        
        int n = num;
        StringBuilder sb = new StringBuilder(2048);
        while(n-- > 0){
            if(n % 100 == 0){
                raf.write(sb.toString().getBytes(cs));
                sb.setLength(0);
            } else {
                sb.append(strs[n % strs.length]);
            }
        }
        
        raf.write(sb.toString().getBytes(cs));
        
        raf.close();
        
        long end = System.currentTimeMillis();
        
        System.out.println("raf " + num + " with " + (end - start));
    }
    
    public static void writer(final int num) throws IOException{
        long start = System.currentTimeMillis();
        
        FileOutputStream os = new FileOutputStream("d:\\2.txt");
        OutputStreamWriter writer = new OutputStreamWriter(os, cs);
        
        int n = num;
        StringBuilder sb = new StringBuilder(2048);
        while(n-- > 0){
            if(n % 100 == 0){
                writer.write(sb.toString());
                writer.flush();
                sb.setLength(0);
            } else {
                sb.append(strs[n % strs.length]);
            }
        }
        
        writer.write(sb.toString());
        writer.flush();
        
        writer.close();
        
        long end = System.currentTimeMillis();
        
        System.out.println("writer " + num + " with " + (end - start));
    }
    
    private static String cs = "UTF-8";
    private static String[] strs = {
        "*3、最后3个byte(6个HEX字符)存储的是每一秒重置一次的计数器值;<br>",
        "因为JUID以时间戳开头的，所以它是近似有序的，如果使用JUID代替UUID做数据库主键，索引插入效率会高很多。<br>",
        "*在同一个进程中每秒最多可以创建16,777,216个不同的JUID。",
        "publicstaticfinalintJUID_HEX_STR_LENGTH=22;",
        "privatestaticfinalLoggerLOG=Logger.getLogger(JUID.class);",
        "privatestaticfinalint_INSTANCE;",
        "privatestaticint_TIMESTAMP;",
        "privatestaticAtomicInteger_COUNTER=newAtomicInteger(0);",
        "privatestaticfinalReentrantReadWriteLock_LOCK=newReentrantReadWriteLock();"
    };
}
