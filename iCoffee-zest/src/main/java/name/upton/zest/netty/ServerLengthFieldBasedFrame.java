package name.upton.zest.netty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class ServerLengthFieldBasedFrame {

    public static void main(String[] args) {
        // Server服务启动器
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newFixedThreadPool(2), Executors.newFixedThreadPool(4)));

        // 设置处理客户端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new LengthFieldPrepender(4),
                        new LengthFieldBasedFrameDecoder(4 * 1024 * 1024, 0, 4, 0, 4), new BusinessHandler());
            }
        });
        // 绑定8000端口供客户端访问
        bootstrap.bind(new InetSocketAddress(8000));
    }

    private static class BusinessHandler extends SimpleChannelHandler {
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            String msg = buffer.toString(Charset.forName("UTF-8"));
            buffer.clear();
            //System.out.println("Receive:" + msg);
            String outMsg = msg.length() + " size msg has been processed!";
            ChannelBuffer buffer2 = ChannelBuffers.buffer(outMsg.length());
            buffer2.writeBytes(outMsg.getBytes());
            e.getChannel().write(buffer2);
            buffer2.clear();
        }
    }

}
