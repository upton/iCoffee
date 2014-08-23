package name.upton.zest.threadpool;

public class ThreadTest {
    private static long size = 0;

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        int n = 10;

        while (n-- > 0) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    int i = 10;
                    while (i-- > 0) {
                        count();
                    }
                }
            }).start();
        }

        Thread.sleep(1000L);
        System.out.println(size);
    }

    public static void count() {
        size = size + 1;
    }

}
