package ats;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import javax.servlet.Servlet;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

public class ServletHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final Servlet servlet;
    private final String charset;

    // 预警开关
    boolean timeoutWarnSwitch = false;

    static Logger infoLogger = LogManager.getLogger("ruby_log");

    public ServletHandler(Servlet servlet, String charset) {
        this.servlet = servlet;
        this.charset = charset;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest)
            throws Exception {
        if (fullHttpRequest.getDecoderResult().isFailure()) {
            sendError(channelHandlerContext, HttpResponseStatus.BAD_REQUEST);
            return;
        }

        MockHttpServletRequest servletRequest = createHttpRequest(fullHttpRequest);
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        servletRequest.setCharacterEncoding(charset);
        servletResponse.setCharacterEncoding(charset);
        servletRequest.setAttribute("handlerContext", channelHandlerContext);

        servlet.service(servletRequest, servletResponse);

    }

    /**
     * 包装HttpServletRequest
     */
    private MockHttpServletRequest createHttpRequest(FullHttpRequest fullHttpRequest) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(fullHttpRequest.getUri()).build();

        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.setRequestURI(uriComponents.getPath());
        servletRequest.setPathInfo(uriComponents.getPath());
        servletRequest.setMethod(fullHttpRequest.getMethod().name());

        if (uriComponents.getScheme() != null) {
            servletRequest.setScheme(uriComponents.getScheme());
        }
        if (uriComponents.getPort() != -1) {
            servletRequest.setServerPort(uriComponents.getPort());
        }
        if (uriComponents.getHost() != null) {
            servletRequest.setServerName(uriComponents.getHost());
        }

        // 请求头
        for (String name : fullHttpRequest.headers().names()) {
            servletRequest.addHeader(name, fullHttpRequest.headers().get(name));
        }

        // 请求内容
        ByteBuf content = fullHttpRequest.content();
        if (content != null && content.hasArray()) {
            byte[] byteContent = content.array();
            servletRequest.setContent(byteContent);
        }

        // post请求参数
        try {
            ByteBuf buf = fullHttpRequest.content();
            int readable = buf.readableBytes();
            byte[] bytes = new byte[readable];
            buf.readBytes(bytes);
            String contentStr = UriUtils.decode(new String(bytes, "UTF-8"), "UTF-8");
            for (String params : contentStr.split("&")) {
                String[] para = params.split("=");
                if (para.length > 1) {
                    servletRequest.addParameter(para[0], para[1]);
                } else {
                    servletRequest.addParameter(para[0], "");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // get请求参数
        try {
            for (java.util.Map.Entry<String, List<String>> entry : uriComponents.getQueryParams().entrySet()) {
                for (String value : entry.getValue()) {
                    if (entry.getKey() != null) {
                        if (value != null) {
                            servletRequest.addParameter(UriUtils.decode(entry.getKey(), charset),
                                    UriUtils.decode(value, charset));
                        } else {
                            servletRequest.addParameter(UriUtils.decode(entry.getKey(), charset), "");
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return servletRequest;
    }

    /**
     * 响应错误信息
     */
    private void sendError(ChannelHandlerContext handlerContext, HttpResponseStatus status) {
        ByteBuf content = Unpooled.copiedBuffer(status.toString(), Charset.forName(charset));
        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, content);
        httpResponse.headers().add(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=" + charset);
        handlerContext.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
    }
}
