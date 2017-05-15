package ats.controller;


import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenlejia 订单操作handle类
 *
 */
public class OrdersHandle extends SuperHandle{

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

    public OrdersHandle(ChannelHandlerContext channelHandlerContext,
                        NettyMessageModel nettyMessageModel) {
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
            logger.error(e.getMessage());
        }
    }

    /**
     * 响应委托下单
     */
    public void responsePlaceOrder() {
        try {
            super.responseNetty(nettyMessageModel, channelHandlerContext, requestId);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage());
        }
    }

    /**
     * 响应已成交订单查询
     */
    public void responseExecutionOrders(List<byte[]> list) {
        try {
            if (list == null) {
                this.getNettyMessageModel()
                        .setErrno(IBMessageDefinition.OrdersDataError.getErrno());
                this.getNettyMessageModel()
                        .setMessage(IBMessageDefinition.OrdersDataError.getMessage());
                return;
            }
            // 将list中存储的byte[]反序列化成ExecutionOrdersModel对象
            List orders = new ArrayList();
            for (byte[] orderByte : list) {
                ExecutionOrdersModel order = (ExecutionOrdersModel) SerializeUtil.unserialize(orderByte);
                orders.add(order);
            }
            ((ListModel)this.getNettyMessageModel().getBody()).setList(orders);
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage());
        }finally{
            super.responseNetty(nettyMessageModel, channelHandlerContext, requestId);
        }
    }
}
