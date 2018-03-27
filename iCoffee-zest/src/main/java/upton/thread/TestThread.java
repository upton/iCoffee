package upton.thread;

public class TestThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                int i = 10;
                while(i-- > 0){
                    System.out.println(i);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
            }
        }).start();

    }

}
