package name.upton.zest;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args){
        List<Bean> list = new ArrayList<Bean>();
        
        Bean jj = new Bean();
        jj.name = "zs";
        jj.age = 20;
        
        Bean kk = new Bean();
        kk.name = "ls";
        kk.age = 18;
        
        list.add(jj);
        list.add(kk);
        
        jj.name = "czh";
        
        if(list.contains(jj)){
            System.out.println(111);
        }
        
        list.add(jj);
        
        if(list.contains(jj)){
            System.out.println(222);
        }
        
        System.out.println(list);
    }
    
    static class Bean{
        public String name;
        public int age;
        
        public String toString(){
            return name + " " + age;
        }
    }
}
