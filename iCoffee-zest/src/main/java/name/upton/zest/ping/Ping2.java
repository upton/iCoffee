package name.upton.zest.ping;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ping2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        StringBuffer buf = new StringBuffer();
        String s = "";
        Process process;
        try {
            process = Runtime.getRuntime().exec("cmd /c " + "ping 192.168.2.181");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            while ((s = br.readLine()) != null) {
                buf.append(s + "\r\n");
            }

            process.waitFor();
            System.out.println(buf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
