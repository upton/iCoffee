package upton;

import java.util.ArrayList;
import java.util.List;

public class Hello {
    public String hi(String name){
        return "hello " + name;
    }
    
    public static void main(String[] args){
        List<String> urls = new ArrayList<String>(){
            {
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
                add("6");
                add("7");
                add("8");
                add("9");
                add("10");
            }
        };
        
        int SEND_BATCH_SIZE = 3;
        int urlsSize = urls.size();
        int start = 0;
        int end = SEND_BATCH_SIZE;
        for(;start < urlsSize && end <= urlsSize; start += SEND_BATCH_SIZE, end = (end + SEND_BATCH_SIZE) > urlsSize ? urlsSize : (end + SEND_BATCH_SIZE)){
            System.out.println(urls.subList(start, end));

    }
    

        Integer i = Integer.valueOf(123);
        Integer j = Integer.valueOf(123);
        
        System.out.println(i == j);
        
                
        System.out.println(String.format("%04d", 12));
    }
}
