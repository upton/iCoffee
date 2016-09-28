package upton.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class TestOKhttp {

    public static void main(String[] args) throws Exception {
        final OkHttpClient client = new OkHttpClient();
        final AtomicInteger count1 = new AtomicInteger(0);
        final AtomicInteger count2 = new AtomicInteger(0);

        client.setConnectTimeout(1000L, TimeUnit.MILLISECONDS);
        client.setWriteTimeout(1000L, TimeUnit.MILLISECONDS);
        client.setReadTimeout(1000L, TimeUnit.MILLISECONDS);

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Request request = new Request.Builder().url("http://127.0.0.1:8528/get_config").get().build();
                        try {
                            Response response = client.newCall(request).execute();

                            if (response.isSuccessful()) {
                                response.body().close();
                                count1.incrementAndGet();
                            } else {
                                System.out.println("get_config error");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Request request = new Request.Builder().url("http://127.0.0.1:8528/get_server_nodes").get().build();
                        try {
                            Response response = client.newCall(request).execute();

                            if (response.isSuccessful()) {
                                response.body().close();
                                count2.incrementAndGet();
                            } else {
                                System.out.println("======get_server_nodes error");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("count1=" + count1.get());
                    System.out.println("count2=" + count2.get());
                }

            }
        }).start();

        Thread.sleep(Long.MAX_VALUE);
    }
}
