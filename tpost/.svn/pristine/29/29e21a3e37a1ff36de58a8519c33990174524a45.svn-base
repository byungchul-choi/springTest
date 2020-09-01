package tpost.SimpleTest.util;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	public static final int MESSAGE_SIZE = 256;
	
	public TimeClientHandler myClient = new TimeClientHandler();

	String host = "";
    public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	int port = 9000;
    
    
	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public void runClient() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
//            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(myClient);
                }
            });
            /**/
            /*
            b.group(workerGroup)//1. 이벤트 루프그룹 설정(서버랑 다르게 1개만 - 연결된 채널이 하나만 존재)
            .channel(NioSocketChannel.class)//2. 채널의 종류설정(NIO 소켓채널로 설정)
            .handler(new ChannelInitializer<SocketChannel>() {//3.채널파이프 라인 설정에 일반 소캣채널 클래스 SocketChannel 설정
              @Override
              protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                p.addLast(new TimeClientHandler());
                 
              }
            });
            
            */
            /**/
            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}