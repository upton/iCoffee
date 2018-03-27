

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(new String[] { "su", "DDW", "-c", "java" ,"-version"});

        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        
        reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        

        System.out.println(process.waitFor());
    }
}
