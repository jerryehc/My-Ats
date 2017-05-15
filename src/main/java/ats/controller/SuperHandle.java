package ats.controller;


import ats.utils.FastJsonTools;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.Charset;

public class SuperHandle {

    Logger infoLogger = LogManager.getLogger("ruby_log");
    /**
     * 发送账户信息查询接口响应消息
     */
    public void responseNetty(Object obj, ChannelHandlerContext channelHandlerContext, long requestId) {
        try {
            // 将nettyMessageModel对象转换成JSON字符串
            String strContenct = FastJsonTools.createJsonString(obj);
            // 消息体
            String charset = "UTF-8";
            ByteBuf content = Unpooled.copiedBuffer(strContenct, Charset.forName(charset));
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    content);
            // httpResponse.content()
            // 指定内容MIME类型，和字符集
            httpResponse.headers().add(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=" + charset);
            // 打印日志
//            LogUtil.tradeLogPrint(Constant.print_type_response_app, requestId, obj);
            // 发送消息后关闭
            channelHandlerContext.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            // TODO: handle exception
            infoLogger.error(e.getMessage(), e);
        }
    }
}
