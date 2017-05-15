package ats;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class AtsServer {

    private final int port;
    private final String charset;
    private final String env = "production"; //"test"
    private final String[] configLocation;

    public AtsServer(int port, String charset, String... configLocation) {
        this.port = port;
        this.configLocation = configLocation;
        this.charset = charset;

    }

    public void start() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(port);
            bootstrap.childHandler(new DispatcherServletInit(charset, env, configLocation));
            bootstrap.bind().sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
