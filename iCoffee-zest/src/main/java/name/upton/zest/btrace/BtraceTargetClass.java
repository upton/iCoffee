package name.upton.zest.btrace;

public class BtraceTargetClass {

    public static void main(String[] args) throws InterruptedException {
        BtraceTargetClass target = new BtraceTargetClass();
        int a = 0;
        while(true){
            System.out.println(target.add(a++, a));
        }
    }
    
    public int add(int x, int y) throws InterruptedException{
        Thread.sleep(1000);
        return x + y;
    }

}
