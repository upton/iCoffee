package name.upton.friday;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;

public class BatchInsert {

    public static void main(String[] args) throws InterruptedException {
        int count = 200000000;

        System.out.println("need insert " + count + " datas");
        BatchInsert bi = new BatchInsert();

        long start = System.currentTimeMillis();

        Data dd = null;
        DataBox box = new DataBox();
        for (int i = 0; i < count; i++) {
            dd = new Data(i, i);
            if (!box.add(dd)) {
                bi.insrt(box);
                box = new DataBox();
            }
        }

        if (box.size > 0) {
            bi.insrt(box);
        }

        System.out.println(System.currentTimeMillis() - start);

        Thread.sleep(2000L);

        int sum = 0;
        for (int i = 0; i < sums.length; i++) {
            sum += sums[i];
        }

        System.out.println("insert with " + sum + " datas");
    }

    private static final int MAX_WORKERS = Runtime.getRuntime().availableProcessors() - 1;
    private ExecutorService executor = Executors.newFixedThreadPool(MAX_WORKERS);
    private static LinkedTransferQueue<DataBox> queue = new LinkedTransferQueue<DataBox>();
    //private static ArrayBlockingQueue<DataBox> queue = new ArrayBlockingQueue<DataBox>(4);
    private static int[] sums = new int[MAX_WORKERS];

    public BatchInsert() {
        System.out.println("Init " + MAX_WORKERS + " threads to work");
        for (int i = 0; i < MAX_WORKERS; i++) {
            executor.execute(new Worker(i));
        }
    }

    public void insrt(DataBox box) {
        try {
            queue.transfer(box);
            //queue.put(box);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Worker implements Runnable {
        private int sumIdx;

        public Worker(int i) {
            sumIdx = i;
            sums[sumIdx] = 0;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    DataBox box = queue.take();
                    doRealBatchInsert(box);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        private void doRealBatchInsert(DataBox box) {
            if (box.size > 0) {
                sums[sumIdx] += box.size;
                for (int i = 0; i < box.size; i++) {
                    Data data = box.datas[i];
                    // do the real batch inserts
                }
            }
        }
    }
}

class Data {
    public Data(int id, int info) {
        this.id = id;
        this.info = info;
    }

    public int id;
    public int info;
}

class DataBox {
    private final int COUNT = 128;
    private final int MAX_IDX = COUNT - 1;
    public Data[] datas = new Data[COUNT];
    public int size = 0;

    /**
     * 
     * @param data
     * @return true: box is not full; false: box is full
     */
    public boolean add(Data data) {
        if (size == MAX_IDX) {
            return false;
        }

        datas[size++] = data;

        if (size == MAX_IDX) {
            return false;
        }

        return true;
    }

    public void clear() {
        size = 0;
    }
}