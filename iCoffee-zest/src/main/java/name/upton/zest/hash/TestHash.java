package name.upton.zest.hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHash {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        Map<Long, String> apps = new HashMap<Long, String>();
//
//        BufferedReader br = new BufferedReader(new FileReader("d:/apps.txt"));
//        String line = null;
//        while ((line = br.readLine()) != null) {
//            if (!(line = line.trim()).equals("")) {
//                Long code = BKDRHash(line);
//                if (apps.containsKey(code)) {
//                    System.out.println("exsits hash code,app=" + line + ",code=" + code);
//                } else {
//                    apps.put(code, line);
//                }
//            }
//        }
//        
//        System.out.println(apps);
        
//        String[] apps = {"paimai",
//                "mobile-app",
//                "ware_knowledge",
//                "domain",
//                "piao",
//                "chat",
//                "ump_all",
//                "eptPrintService",
//                "ill-wms",
//                "wmsDeliery"};
//        
//        int length = apps.length;
//        
//        long start = System.currentTimeMillis();
//        for(int i = 0; i < 100000000; i++){
//            DJBHash(apps[i % length]);
//        }
//
//        long end = System.currentTimeMillis();
//        
//        System.out.println(end - start);
        
        String[] ss = {"1","2","3"};
        
        String[] nSS = Arrays.copyOf(ss, 4);
        nSS[nSS.length - 1] = "4";
        
        System.out.println(Arrays.asList(nSS));
    }

    public static int BKDRHash(String str) {
        int seed = 131; // 31 131 1313 13131 131313 etc..
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * seed) + str.charAt(i);
        }
        return hash;
    }

    public static long DJBHash(String str) {
        long hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }
        return hash;
    }
}