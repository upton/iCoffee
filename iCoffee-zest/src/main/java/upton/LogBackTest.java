package upton;

public class LogBackTest {

    public static void main(String[] args) throws InterruptedException {
        new Log1().test();
        new Log2().test();
        
        Thread.sleep(4000L);
    }

}
