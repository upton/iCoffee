package name.upton.zest.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MD5 {

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<String, AtomicInteger> counter = new HashMap<String, AtomicInteger>();
        Set<String> countSet = new HashSet<String>();
        
        int i = 10000000;

//        while (i-- > 0) {
//            String ss = "" + i + "loghub.JCOLLECTOR_JVM";
//            
//            ss = Long.toHexString(DJBHash.hash(ss));
//            
//            String perfix = ss.substring(0, 2);
//            if(counter.containsKey(perfix)){
//                counter.get(perfix).incrementAndGet();
//            } else {
//                counter.put(perfix, new AtomicInteger(1));
//            }
//        }
        
        
        
        while (i-- > 0) {
            

            String perfix = createUidPerfix(3);
            if (counter.containsKey(perfix)) {
                counter.get(perfix).incrementAndGet();
            } else {
                counter.put(perfix, new AtomicInteger(1));
            }
        }
        
        System.out.println("" + counter.size() + counter);
        
//        for(int j = 0; j < 256; j++){
//            String pp = Integer.toHexString(j);
//            
//            if(pp.length() == 1){
//                pp = "0" + pp;
//            }
//            
//            if(!counter.containsKey(pp)){
//                System.out.println(pp);
//            }
//        }
//        
//        System.out.println(Long.toHexString(1362042000L));
    }
    
    private final static char[] UID_PERFIX_STRS = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    private static Random RD = new Random(System.currentTimeMillis());
    
    private static String createUidPerfix(int length){
        StringBuilder perfixNum = new StringBuilder();
        for(int i = 0; i < length; i++){
            perfixNum.append(UID_PERFIX_STRS[RD.nextInt(UID_PERFIX_STRS.length)]);
        }
        
        return perfixNum.toString();
    }

    public static String md5(String plainText) throws NoSuchAlgorithmException {
        return md5(plainText, false);
    }

    public static String md5(String plainText, boolean is16Bit) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        String result = buf.toString();
        if (is16Bit) {
            result = result.substring(8, 24);
        }

        return result;
    }
}
