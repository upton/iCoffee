package name.upton.zest.netty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class ClientLengthFieldBasedFrame {

    public static void main(String args[]) throws InterruptedException {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(Executors.newFixedThreadPool(2), Executors.newFixedThreadPool(4)));
        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new LengthFieldPrepender(4),
                        new LengthFieldBasedFrameDecoder(4 * 1024 * 1024, 0, 4, 0, 4), new RequestHandler());
            }
        });
        // 连接到本地的8000端口的服务端
        final ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final CountDownLatch cdl = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        
        if (future.isSuccess()) {
            final Random rd = new Random();
            for (int i = 0; i < 10; i++) {
                final int idx = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int num = 1_000_000;
                        while (num-- > 0) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Request-").append(idx).append("-");

                            int rSize = rd.nextInt(50) + 1;
                            while (rSize-- > 0) {
                                sb.append('X');
                            }

                            ChannelBuffer buffer = ChannelBuffers.buffer(sb.toString().length());
                            buffer.writeBytes(sb.toString().getBytes());
                            future.getChannel().write(buffer);
                            buffer.clear();
                        }
                        
                        cdl.countDown();
                    }
                }).start();
            }
        }
        
        cdl.await();
        
        System.out.println(System.currentTimeMillis() - start);
    }

    private static class RequestHandler extends SimpleChannelHandler {

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            //System.out.println(buffer.toString(Charset.forName("UTF-8")));
            buffer.clear();
        }
    }

}
