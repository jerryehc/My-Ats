package ats.controller;

import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author chenlejia 账户信息handle类
 *
 */
public class LoginHandle extends SuperHandle {

    // 日志对象
    private Logger logger = LogManager.getLogger("ruby_log");
    private ChannelHandlerContext channelHandlerContext;
    private NettyMessageModel nettyMessageModel;
    private long requestId;

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }

    public NettyMessageModel getNettyMessageModel() {
        return nettyMessageModel;
    }

    public void setNettyMessageModel(NettyMessageModel nettyMessageModel) {
        this.nettyMessageModel = nettyMessageModel;
    }

    public LoginHandle(ChannelHandlerContext channelHandlerContext, NettyMessageModel nettyMessageModel) {
        this.channelHandlerContext = channelHandlerContext;
        this.nettyMessageModel = nettyMessageModel;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    /**
     * 通用响应函数
     */
    public void response() {
        try {
            super.responseNetty(nettyMessageModel, channelHandlerContext, requestId);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
        }
    }

//    public NettyMessageModel verificationPwd(String custNo,String passwd,String apiVersion) {
//        String url = PropertiesUtil.getPropertyValue("validatePasswdUrl");
//        try {
//            Map<String, String> propertys = new HashMap<String, String>();
//            propertys.put("API-VERSION", apiVersion);
//            Map<String, Object> paramsLogin = new HashMap<String, Object>();
//            paramsLogin.put("cust_no", custNo);
//            paramsLogin.put("passwd", passwd);
//            HttpResponse response_dataLogin = HttpsRequester.doSend(url, paramsLogin,
//                    propertys);
//            if(response_dataLogin!=null){
//                logger.info("验密接口响应信息："+response_dataLogin.getContent());
//                NettyMessageModel obj=JSON.parseObject(response_dataLogin.getContent(), NettyMessageModel.class);
//                return obj;
//            }else{
//                return null;
//            }
//
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return null;
//        }
//    }
}
