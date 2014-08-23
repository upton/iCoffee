package name.upton.zest;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AllTest {

    /**
     * @param args
     * @throws ParseException 
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws ParseException, UnsupportedEncodingException {
        testInteger();

    }
    
    public static void testInteger() throws ParseException, UnsupportedEncodingException{
        System.out.println(Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE);  // true
        System.out.println((3 << 16));
        System.out.println((true & true));
        
        Set<Integer> iii = new HashSet<Integer>();
        
        int count = 100;
        int loop = count;
        while(loop-- > 0){
            iii.add(new Random().nextInt());
        }
        
        System.out.println(iii.size());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-01 00:00:00").getTime());
        
        //System.out.println(Integer.parseInt("e4b053a2", 16));
        System.out.println(Integer.toHexString(Integer.MAX_VALUE));
        
        long a  =10000000*1000L;
        System.out.println(a);
        
        String s = "中文123";
        
        System.out.println(s.length());
        System.out.println(s.getBytes("UTF-8").length);
        
        System.out.println(Math.pow(3,2));
        System.out.println(Math.pow(3,3));
        System.out.println(Math.pow(3,4));
    }
}
