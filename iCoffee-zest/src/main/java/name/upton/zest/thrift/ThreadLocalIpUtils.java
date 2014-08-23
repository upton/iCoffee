package name.upton.zest.thrift;

/**
 * 
 * @author chenzehong
 *
 */
public class ThreadLocalIpUtils {
    private static ThreadLocal<String> IP_HOLDER_selector = new ThreadLocal<String>();
    private static ThreadLocal<String> IP_HOLDER_worker = new ThreadLocal<String>();
    
    public static void setIp(String ip){
        IP_HOLDER_selector.set(ip);
    }
    
    public static String getIp(){
        return IP_HOLDER_selector.get();
    }
    
    public static void remove(){
        IP_HOLDER_selector.remove();
    }
    
    public static void setWorkerIp(String ip){
        IP_HOLDER_worker.set(ip);
    }
    
    public static String getWorkerIp(){
        return IP_HOLDER_worker.get();
    }
    
    public static void removeWorkerIp(){
        IP_HOLDER_worker.remove();
    }
}