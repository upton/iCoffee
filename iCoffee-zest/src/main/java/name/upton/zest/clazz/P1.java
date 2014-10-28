package name.upton.zest.clazz;

public class P1 {
    private Class cla = getClass();

    public void foo(){
        System.out.println(getClass().getName());
    }
    
    public void bar(){
        System.out.println(cla.getName());
    }
}
