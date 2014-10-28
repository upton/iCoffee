package name.upton.zest.clazz;

public class P11 extends P1 {
    private Class cla = getClass();

    public void foo11(){
        System.out.println(getClass().getName());
    }
    
    public void bar11(){
        System.out.println(cla.getName());
    }
}
