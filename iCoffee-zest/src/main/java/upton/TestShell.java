package upton;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestShell {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("/Users/upton/test.sh");

        pb.directory(new File("/Users/upton"));

        final Process p = pb.start();

//        int i = p.waitFor();
//
//        System.out.println(i);
        
        new Thread(new Runnable() {
            
            public void run() {
                try {
                    Thread.sleep(8000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                p.destroy();
                
            }
        }).start();

        String line = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        
        System.out.println(p.waitFor());
    }

}
