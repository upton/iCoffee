package name.upton.zest.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class CopyOfTestRouting {

    private static Random rrd = new Random(System.currentTimeMillis());

    /**
     * @param args
     */
    public static void main(String[] args) {
        String apps = "ump_all,search-360buy,etms-waybill,mobileLog,jss,orderbank_soa_360buy_com,bluedragon-distribution-worker";

        Random rd = new Random(System.currentTimeMillis());

        for (int k = 2; k <= 5; k++) {
            for (int j = 0; j < 10000; j++) {
                int suffixSize = 6;
                String[] suffixs = getSuffixs(k, suffixSize);
                Map<Long, Integer> cAll = new HashMap<Long, Integer>();
                for (String app : apps.split(",")) {
                    for (int i = 0; i < 100000; i++) {
                        long route = Math.abs(DJBHash.hash(app + suffixs[rd.nextInt(suffixs.length)])) % 20;

                        Integer rAll = cAll.get(route);
                        if (rAll == null) {
                            rAll = 1;
                        } else {
                            rAll++;
                        }

                        cAll.put(route, rAll);
                    }
                }

                boolean goodSuffix = true;
                for (Entry<Long, Integer> entry : cAll.entrySet()) {
                    if (entry.getValue() > 45000) {
                        goodSuffix = false;
                        break;
                    }
                }

                if (goodSuffix) {
                    System.out.println(cAll.size() + ":" + cAll + ",suffixs=" + Arrays.asList(suffixs));
                }
                
                if(cAll.size() == 20){
                    System.out.println(cAll.size() + ":size" + cAll + ",suffixs=" + Arrays.asList(suffixs));
                }
            }
        }
    }

    public static String[] getSuffixs(int length, int suffixSize) {
        String[] ss = new String[suffixSize];
        String[] words = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        for (int i = 0; i < ss.length - 1; i++) {
            ss[i] = "";
            for (int j = 0; j < length; j++) {
                if (rrd.nextBoolean()) {
                    ss[i] += words[rrd.nextInt(words.length)].toUpperCase();
                } else {
                    ss[i] += words[rrd.nextInt(words.length)];
                }
            }
        }

        ss[ss.length - 1] = "";
        return ss;
    }
}
