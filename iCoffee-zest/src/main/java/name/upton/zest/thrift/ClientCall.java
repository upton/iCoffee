package name.upton.zest.thrift;

import name.upton.zest.thrift.log.Scribe.Client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

public class ClientCall implements Runnable {

    /**
     * @param args
     * @throws TException
     */
    public static void main(String[] args) {
        String ip = "10.28.160.14";
        String ip2 = "127.0.0.1";
        for (int i = 0; i < 50; i++) {
            new Thread(new ClientCall(i % 2 == 0 ? ip : ip2)).start();
        }
    }
    
    private String ip;
    
    public ClientCall(String ip){
        this.ip = ip;
    }

    @Override
    public void run() {
        TSocket transport = new TSocket(ip, 12345, 2000);
        TFramedTransport frameTransport = new TFramedTransport(transport);
        TBinaryProtocol protocol = new TBinaryProtocol(frameTransport);
        try {
            frameTransport.open();
        } catch (TTransportException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Client client = new Client(protocol);
        while (true) {

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
            }

            try {
                System.out.println(client.getOption("1234"));
                if(!ip.equals(client.getOption("1234"))){
                    System.out.println("bad~~~~~~~~~~~~~~~~");
                }
            } catch (TException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
