package info.xpanda.labs.nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

    private final String host;

    private int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception{
        //Netty线程模型，事件循环组
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            // 引导
            Bootstrap b = new Bootstrap();
            b.group(group)
                    //Socket通道
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 将客户端句柄添加到通道中的管道链
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            //异步通知
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        if(args.length != 2){
            System.err.println("Usage: " + EchoClient.class.getSimpleName()
            + " <host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        new EchoClient(host, port).start();
    }
}
