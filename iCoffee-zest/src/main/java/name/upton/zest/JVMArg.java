package name.upton.zest;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.List;

public class JVMArg {

    private static HashMap<String, byte[]> hh = new HashMap<String, byte[]>();

    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 2048; i++) {
                    Object[] ss = new Object[2048];
                    HashMap<String, String> hh = new HashMap<String, String>();

                    for (int j = 0; j < 2048; j++) {
                        hh.put("k" + j, "v" + j);
                    }

                    ss[i] = hh;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 2048; i++) {
                    Object[] ss = new Object[2048];
                    HashMap<String, String> hh = new HashMap<String, String>();

                    for (int j = 0; j < 2048; j++) {
                        hh.put("k" + j, "v" + j);
                    }

                    ss[i] = hh;
                }
            }
        }).start();

        while (true) {
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage memUsage = memoryMXBean.getHeapMemoryUsage();

            List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();

            for (int i = 0; i < memoryPoolMXBeans.size(); i++) {
                MemoryPoolMXBean memoryPoolMXBean = memoryPoolMXBeans.get(i);
                MemoryUsage usage = memoryPoolMXBean.getUsage();
                MemoryUsage peakUsage = memoryPoolMXBean.getPeakUsage();
                System.out.println(peakUsage.getCommitted());
            }

            Thread.sleep(10L);
        }

    }

}
