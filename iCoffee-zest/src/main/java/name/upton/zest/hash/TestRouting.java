package name.upton.zest.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TestRouting {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String apps = "etms-waybill,ump_all,ump_drc_log,search-360buy,jss";
        String[] sf = "fuCk, shiT, RI, ChA0, GaN".split(",");
        
        for(int i = 0; i < sf.length; i++){
            sf[i] = sf[i].trim();
        }
        
        String[] suffixs = Arrays.copyOf(sf, sf.length + 1);
        suffixs[suffixs.length - 1] = "";
        
        Random rd = new Random(System.currentTimeMillis());

        Map<Long, Integer> cAll = new TreeMap<Long, Integer>();
        for (String app : apps.split(",")) {  
            Map<Long, Integer> counter = new TreeMap<Long, Integer>();
            for (int i = 0; i < 100000; i++) {
                long route = Math.abs(DJBHash.hash(app + suffixs[rd.nextInt(suffixs.length)])) % 20;
                
                Integer r = counter.get(route);
                if(r == null){
                    r = 1;
                } else {
                    r++;
                }
                
                counter.put(route, r);
                                
                Integer rAll = cAll.get(route);
                if(rAll == null){
                    rAll = 1;
                } else {
                    rAll++;
                }
                
                cAll.put(route, rAll);
            }
            
            System.out.println(app + ":" + counter);            
        }
        System.out.println("All:" + cAll);
        System.out.println(Arrays.asList(suffixs));
    }
}
