package ats.controller;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017-04-10.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    @ResponseBody
    public void login(HttpServletRequest request, @RequestParam Map<String,String> params){
        LoginHandle loginHandle = null;
        // 此对象是netty请求对象，ib异步响应消息后再回调函数中用此对象返回消息给netty客户端
        ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext) request.getAttribute("handlerContext");
        try {
            // 交易唯一标识
            long requestId = System.currentTimeMillis();


            loginHandle = new LoginHandle(channelHandlerContext, new NettyMessageModel());
            loginHandle.setRequestId(requestId);

            NettyMessageModel obj = new NettyMessageModel();
            obj.setErrno("0");
            obj.setMessage("Sucess");
            loginHandle.response();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loginHandle.response();
        }
    }

}
