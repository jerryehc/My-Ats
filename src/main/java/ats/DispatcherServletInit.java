package ats;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 服务启动控制器
 */
public class DispatcherServletInit extends ChannelInitializer<SocketChannel> {

    private final DispatcherServlet dispatcherServlet;
    private final String charset;
    private final int readTimeOut = PropertiesUtil.getPropertyValueInt("readTimeOut", 3000);
    private final int writeTimeOut = PropertiesUtil.getPropertyValueInt("writeTimeOut", 3000);

    /**
     * 构造器，初始化上下文
     */
    public DispatcherServletInit(String charset, String env, String... configLocation) throws ServletException {
        this.charset = charset;

        MockServletContext servletContext = new MockServletContext();
        MockServletConfig servletConfig = new MockServletConfig(servletContext);

        XmlWebApplicationContext wac = new XmlWebApplicationContext();
        wac.setServletContext(servletContext);
        wac.setServletConfig(servletConfig);
        wac.setConfigLocations(configLocation);
        wac.getEnvironment().addActiveProfile(env);

        this.dispatcherServlet = new DispatcherServlet(wac);
        this.dispatcherServlet.init(servletConfig);

    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();


        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
        pipeline.addLast("readTimeout", new ReadTimeoutHandler(readTimeOut, TimeUnit.MILLISECONDS));
        pipeline.addLast("writeTimeout", new WriteTimeoutHandler(writeTimeOut, TimeUnit.MILLISECONDS));
        pipeline.addLast("handler", new ServletHandler(dispatcherServlet, charset));
    }
}
