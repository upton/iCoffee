package name.upton.zest.id;

import java.util.Arrays;
import java.util.Random;

/**
 * 为JUID提供前缀支持、以及为有前缀的JUID字符串提供转换的工具
 * 
 * @author chenzehong
 * 
 */
public class JUIDUtil {
    private static Random RD = new Random(System.currentTimeMillis());

    /**
     * 转换可能包含前缀的JUID形式hexString转为byte[]
     * 
     * @param hexStr
     *            可能包含前缀的JUID，前缀长度可以为：0,2,4,6,8
     * @return 转换后的
     */
    public static byte[] convertJuidHexStr2Bytes(String hexStr) {
        if (hexStr == null) {
            throw new IllegalArgumentException("hex string must not null");
        }

        if (hexStr.length() < JUID.JUID_HEX_STR_LENGTH) {
            throw new IllegalArgumentException("hex string length must great and equal " + JUID.JUID_HEX_STR_LENGTH);
        }

        int perfixLeng = hexStr.length() - JUID.JUID_HEX_STR_LENGTH;
        if (perfixLeng != 0 && perfixLeng != 2 && perfixLeng != 4 && perfixLeng != 6 && perfixLeng != 8) {
            throw new IllegalArgumentException("hex string length is wrong, must be " + JUID.JUID_HEX_STR_LENGTH
                    + " + (0 or 2 or 4 or 6 or 8)");
        }

        if (perfixLeng > 0) {
            // 有前缀
            String perfix = hexStr.substring(0, perfixLeng);
            String juidStr = hexStr.substring(perfixLeng);

            int perfixBytesLength = perfix.length() / 2;
            byte[] perfixBytes = new byte[perfixBytesLength];
            int perfixInt = Integer.parseInt(perfix, 16);

            for (int i = perfixBytesLength - 1, j = 0; i >= 0; i--, j++) {
                perfixBytes[j] = (byte) (perfixInt >> (i * 8));
            }

            byte[] juidBytes = JUID.convertHexStr2Bytes(juidStr);
            byte[] result = Arrays.copyOf(perfixBytes, perfixBytes.length + juidBytes.length);
            System.arraycopy(juidBytes, 0, result, perfixBytes.length, juidBytes.length);

            return result;
        } else {
            // 没有前缀
            return JUID.convertHexStr2Bytes(hexStr);
        }
    }

    /**
     * 转换byte数组为Hex字符串
     * @param bytes
     * @return
     */
    public static String convertBytes2HexStr(byte[] bytes) {
        if(bytes == null || bytes.length <= 0){
            return null;
        }
        
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            int x = bytes[i] & 0xFF;
            String s = Integer.toHexString(x);

            if (s.length() == 1) {
                buf.append("0");
            }

            buf.append(s);
        }

        return buf.toString();
    }

    /**
     * 创建指定长度的JUID前缀
     * 
     * @param perfixLeng
     *            前缀长度，只能是：2、4、6、8之一
     * @return
     */
    public static String createPerfix(int perfixLeng) {
        if (perfixLeng != 2 && perfixLeng != 4 && perfixLeng != 6 && perfixLeng != 8) {
            throw new IllegalArgumentException("perfixLeng is wrong, must be 2 or 4 or 6 or 8");
        }

        int byteLeng = perfixLeng / 2;
        byte[] bb = new byte[byteLeng];

        RD.nextBytes(bb);

        StringBuilder buf = new StringBuilder(perfixLeng);

        for (int i = 0; i < bb.length; i++) {
            int x = bb[i] & 0xFF;
            String s = Integer.toHexString(x);

            if (s.length() == 1) {
                buf.append("0");
            }

            buf.append(s);
        }

        return buf.toString();
    }
}