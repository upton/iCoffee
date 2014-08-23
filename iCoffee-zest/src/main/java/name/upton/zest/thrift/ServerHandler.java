package name.upton.zest.thrift;

import java.util.List;
import java.util.Map;

import name.upton.zest.thrift.log.LogEntry;
import name.upton.zest.thrift.log.ResultCode;
import name.upton.zest.thrift.log.SubscribeFlag;
import name.upton.zest.thrift.log.SubscribeType;

import org.apache.thrift.TException;

import com.facebook.fb303.fb_status;


public class ServerHandler implements name.upton.zest.thrift.log.Scribe.Iface{

    @Override
    public long aliveSince() throws TException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getCounter(String arg0) throws TException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Map<String, Long> getCounters() throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getCpuProfile(int arg0) throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getOption(String arg0) throws TException {
        String ip = ThreadLocalIpUtils.getWorkerIp();
        ThreadLocalIpUtils.removeWorkerIp();
        if(ip == null) System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return ip;
    }

    @Override
    public Map<String, String> getOptions() throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public fb_status getStatus() throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getStatusDetails() throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getVersion() throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reinitialize() throws TException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setOption(String arg0, String arg1) throws TException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void shutdown() throws TException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ResultCode Log(List<LogEntry> messages) throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<LogEntry> getLog(String category) throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultCode subscribe(String orderId, String category, SubscribeType store, List<String> addresses, SubscribeFlag flag,
            boolean isBalance, int count) throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Long> getCagetoryFlow(String category) throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getConfile() throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean setConfile(String content) throws TException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<String> getSubcategory(String category) throws TException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean stopServer() throws TException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean startServer() throws TException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getCurrentConns() throws TException {
        // TODO Auto-generated method stub
        return 0;
    }

}
