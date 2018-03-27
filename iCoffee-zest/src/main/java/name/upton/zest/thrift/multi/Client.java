package name.upton.zest.thrift.multi;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;

public class Client {

    public static void main(String[] args) throws TException {
        TSocket transport = new TSocket("localhost", 9090);
        transport.open();

        TBinaryProtocol protocol = new TBinaryProtocol(transport);
//
//        TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol, "Hello");
//        Hello.Client helloService = new Hello.Client(mp);
//
//        TMultiplexedProtocol mp2 = new TMultiplexedProtocol(protocol, "Monitor");
//        Monitor.Client monitorService = new Monitor.Client(mp2);

//        System.out.println(helloService.hi("upton"));
//        System.out.println(monitorService.test());
    }

}
