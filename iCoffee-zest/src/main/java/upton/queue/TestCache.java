package upton.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class TestCache {

    public static void main(String[] args) {
        final ArrayBlockingQueue<String> cache = new ArrayBlockingQueue<String>(100);

        int i = Integer.MAX_VALUE;
        while (i-- > 0) {
            if (cache.contains(String.valueOf(i))) {

            } else {
                if (cache.offer(String.valueOf(i))) {
                    cache.poll();
                }
            }
        }

    }

}
