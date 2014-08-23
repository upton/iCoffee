package name.upton.zest.ping;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping3 {

    /**
     * @param args
     * @throws IOException 
     * @throws UnknownHostException 
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
//        String host = "192.168.2.181";
//        int timeOut = 3000;
//        boolean status = InetAddress.getByName(host).isReachable(timeOut);
//        
//        System.out.println(status);

        // InetAddress.isReachable()方法实现了ICMP,也就是Ping.（需要有一定的系统权限）
        
        System.out.println(String.format("%s10", "abc"));

    }

}
