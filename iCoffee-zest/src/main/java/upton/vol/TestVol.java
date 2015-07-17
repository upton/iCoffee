package upton.vol;

public class TestVol {

    public boolean a = false;
    
    
    public static void main(String[] args) {
        final TestVol t = new TestVol();
        new Thread(new Runnable() {
            
            public void run() {
                t.a = true;
                try {
                    Thread.sleep(5 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        
        new Thread(new Runnable() {
            
            public void run() {
                try {
                    Thread.sleep(1 * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println(t.a);
            }
        }).start();
        
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
