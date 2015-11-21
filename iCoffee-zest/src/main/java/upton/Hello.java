package upton;

public class Hello {
    public String hi(String name){
        return "hello " + name;
    }
    
    public static void main(String[] args){
        Integer i = Integer.valueOf(123);
        Integer j = Integer.valueOf(123);
        
        System.out.println(i == j);
        
        Integer k = null;
        
        if(k == 5){
            System.out.println(k);
        }
    }
}
