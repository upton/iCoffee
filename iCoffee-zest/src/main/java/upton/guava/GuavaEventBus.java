package upton.guava;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;

public class GuavaEventBus {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AsyncEventBus eventBus = new AsyncEventBus("upton-bus", Executors.newFixedThreadPool(3));

        eventBus.register(new Object() {
            @Subscribe
            public void helle(String name) {
                //System.out.println(Thread.currentThread().getName() + " : hello " + name);
            }
        });

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            eventBus.post(String.valueOf(i));
        }
    }

}
