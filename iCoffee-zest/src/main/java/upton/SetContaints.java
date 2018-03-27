package upton;
import java.util.Set;
import java.util.HashSet;

public class SetContaints {

    public static void main(String[] args) {
        Set<String> uids = new HashSet<>();
        
       uids.add("1");
       uids.add("2");
       uids.add("3");
       
       String uidStr = "2";
       Long uid = 2L;
       
       System.out.println(uids.contains(uidStr));
       System.out.println(uids.contains(uid));
       
       Integer a = 127;
       Integer b = 127;
       
       Integer x = 128;
       Integer y = 128;
       
       
       System.out.println(a == b);
       System.out.println(a == 127);
       
       System.out.println(x == y); 
       System.out.println(x == 128); 
    }

}
