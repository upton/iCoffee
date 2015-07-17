package name.upton.zest.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.server.TThreadedSelectorServerWithIP;
import org.apache.thrift.transport.TTransportException;

public class Server {

    /**
     * @param args
     * @throws TTransportException
     */
    public static void main(String[] argss) throws TTransportException {
        // 传输层:TNonblockingServerSocket是构建非阻塞 socket
        TNonblockingServerSocket socket = new TNonblockingServerSocket(12345);
        TProcessor processor = null;//new Scribe.Processor<Scribe.Iface>(new ServerHandler());

        TThreadedSelectorServer.Args args = new Args(socket);
        // 设置工作线程数为CPU核数
        int workTheadCount = 16;
        args.selectorThreads(workTheadCount);
        args.workerThreads(workTheadCount);
        args.processor(processor);

        TServer server = new TThreadedSelectorServerWithIP(args);
        server.serve();
    }

}
