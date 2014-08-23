package name.upton.zest.threadpool;

public class MyTask implements Runnable{

    private String name;
    public MyTask(String name){
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + ";" + Thread.currentThread().getName() + ": name=" +name);
    }
}
