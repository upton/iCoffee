package name.upton.zest.ip;

import java.io.IOException;
import java.io.RandomAccessFile;

public class IPSeeker2 {

    protected RandomAccessFile ipDataFile;
    protected final int RECORD_LEN = 7;
    protected final int MODE_1 = 0x01; // 重定向国家记录，地区记录
    protected final int MODE_2 = 0x02; // 重定向国家记录，有地区记录
    protected final int MODE_3 = 0x03; // default
    protected long indexBegin;
    protected long indexEnd;

    public IPSeeker2() throws Exception { // 打开纯真IP库数据文件
        ipDataFile = new RandomAccessFile(IPSeeker2.class.getResource("qqwry.dat").getFile(), "r");
        indexBegin = readLong(4, 0);
        indexEnd = readLong(4, 4);
    }

    public static void main(String[] args) throws Exception {
        IPSeeker2 seeker = new IPSeeker2();// may throw Exception
        String result = seeker.search("122.224.68.242");// 输入查询的IP地址
        System.out.println(result);
        seeker.close();// 关闭,若不调用close，将在finalize关闭
        seeker = null;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            ipDataFile.close();
        } catch (IOException e) {
        }
        super.finalize();
    }

    public void close() {
        try {
            ipDataFile.close();
        } catch (IOException e) {
        }
    }

    public String search(String ipStr) throws Exception { // 采用二分法查询
        long recordCount = (indexEnd - indexBegin) / 7 + 1;
        long itemStart = 0;
        long itemEnd = recordCount - 1;
        long ip = IPSeeker2.stringIP2Long(ipStr);
        long middle = 0;
        long midIP = 0;
        while (itemStart <= itemEnd) {
            middle = (itemStart + itemEnd) / 2;
            midIP = readLong(4, indexBegin + middle * 7);
            if (midIP == ip) {
                break;
            } else if (midIP < ip) {
                itemStart = middle + 1;
            } else// midIP > ip
            {
                itemEnd = middle - 1;
            }
        }

        // 若无完全匹配结果，则向前匹配
        if ((ip < midIP) && middle > 0) {
            middle--;
        }
        long item = readLong(3, indexBegin + middle * 7 + 4);
        String[] result = getInfo(item + 4);// 取出信息
        for(String r : result){
            System.out.println(r);
        }
        return long2StringIP(readLong(4, indexBegin + middle * 7)) + ","// 匹配到的IP地址(段)
                + result[0] + "," // 国家
                + result[1];// 地区
    }

    // 32位整型格式的IP地址(little-endian)转化到字符串格式的IP地址
    public static String long2StringIP(long ip) {
        long ip4 = ip >> 0 & 0x000000FF;
        long ip3 = ip >> 8 & 0x000000FF;
        long ip2 = ip >> 16 & 0x000000FF;
        long ip1 = ip >> 24 & 0x000000FF;
        return String.valueOf(ip1) + "." + String.valueOf(ip2) + "." + String.valueOf(ip3) + "." + String.valueOf(ip4);
    }

    // 字符串格式的IP地址转化到32位整型格式的IP地址(little-endian)
    public static Long stringIP2Long(String ipStr) throws Exception {
        String[] list = ipStr.split("\\.");
        if (list.length != 4) {
            throw new Exception("IP地址格式错误");
        }
        long ip = Long.parseLong(list[0]) << 24 & 0xFF000000;
        ip += Long.parseLong(list[1]) << 16 & 0x00FF0000;
        ip += Long.parseLong(list[2]) << 8 & 0x0000FF00;
        ip += Long.parseLong(list[3]) << 0 & 0x000000FF;
        return ip;
    }

    // 读取一个n位的
    private long readLong(int nByte, long offset) throws Exception {
        ipDataFile.seek(offset);
        long result = 0;
        if (nByte > 4 || nByte < 0)
            throw new Exception("nBit should be 0-4");
        for (int i = 0; i < nByte; i++) {
            result |= ((long) ipDataFile.readByte() << 8 * i) & (0xFFL << 8 * i);
        }
        return result;
    }

    private String[] getInfo(long itemStartPos) throws Exception {
        // result[0]放国家，result[1]放地区
        String[] result = new String[2];
        ipDataFile.seek(itemStartPos);
        int mode = (int) ipDataFile.readByte();
        switch (mode) {
        case MODE_1: {
            long offset = itemStartPos + 1;
            long redirPos = readLong(3, offset);
            result = getInfo(redirPos);
        }
            break;
        case MODE_2: {
            long offset = itemStartPos + 1;
            long redirPos = readLong(3, offset);
            result = getInfo(redirPos);
            result[1] = getArea(offset + 3);
        }
            break;
        default:// MODE_3
        {
            long offset = itemStartPos;
            int countryLen = getStrLength(offset);
            result[0] = getString(offset, countryLen);
            offset = itemStartPos + countryLen + 1;
            result[1] = getArea(offset);
        }
            break;
        }
        return result;
    }

    private String getArea(long offset) throws Exception {
        ipDataFile.seek(offset);
        int cityMode = (int) ipDataFile.readByte();
        if (cityMode == MODE_2 || cityMode == MODE_1) {
            offset = readLong(3, offset + 1);
        }
        int cityLen = getStrLength(offset);
        return getString(offset, cityLen);
    }

    private int getStrLength(long pos) throws IOException {
        ipDataFile.seek(pos);
        long strEnd = pos - 1;
        while (ipDataFile.readByte() != (byte) 0) {
            strEnd++;
        }
        return (int) (strEnd - pos + 1);
    }

    private String getString(long pos, int len) throws IOException {
        byte buf[] = new byte[len];
        ipDataFile.seek(pos);
        ipDataFile.read(buf);
        String s = new String(buf, "gbk");
        return s;
    }
}
