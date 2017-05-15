package ats.controller;

import ats.Main;
import com.ib.client.Contract;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.OrderStatus;
import com.ib.controller.ApiController;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static ats.controller.SerializeUtil.infoLogger;

/**
 * Created by Administrator on 2017-04-11.
 */
@Controller
public class OrderController {

    @RequestMapping(value = "/placeOrder2")
    @ResponseBody
    public void placeOrder2(HttpServletRequest request, @RequestBody Contract contract){
//        System.out.println(contract.symbol());
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

    @RequestMapping(value = "/placeOrder")
    @ResponseBody
    public void placeOrder(HttpServletRequest request, @RequestParam Map<String,String> params){
        // 账户信息查询业务操作类，用于组装消息并返回给netty客户端
        OrdersHandle ordersHandle = null;
        // 此对象是netty请求对象，ib异步响应消息后再回调函数中用此对象返回消息给netty客户端
        ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext) request.getAttribute("handlerContext");
        try {
            // 交易唯一标识
            long requestId = System.currentTimeMillis();
            // 打印日志
//            LogUtil.tradeLogPrint(Constant.print_type_request_app, "placeOrder", requestId, params);

            // 资金账号
            String account = params.get("fund_account");
            // 交易所类型
            String exchange = params.get("exchange");
            String currency = params.get("currency");
            String secType = params.get("secType");
            // 证券代码
            String symbol = params.get("symbol");
            // 委托数量
            String quantity = params.get("quantity");
            // 委托价格
            String price = params.get("price");
            // 买卖方向 规则：买入="1"，卖出="2"
            String action = params.get("action");

//            String entrust_prop = params.get("entrust_prop");
            String orderType = params.get("orderType");
            // 客户号
            String custNo = params.get("cust_no");
            // passwd
            String passwd = params.get("passwd");

            ordersHandle = new OrdersHandle(channelHandlerContext, new NettyMessageModel(new PlaceOrdersModel()));
            ordersHandle.setRequestId(requestId);
            if ("".equals(passwd) || passwd == null) {
                ordersHandle.getNettyMessageModel().setErrno(IBMessageDefinition.NoPwdError.getErrno());
                ordersHandle.getNettyMessageModel().setMessage(IBMessageDefinition.NoPwdError.getMessage());
                ordersHandle.response();
//            } else if (!GlobalCache.accountInfoMap.containsKey(account)) {
//                ordersHandle.getNettyMessageModel().setErrno(IBMessageDefinition.AccUnKnownError.getErrno());
//                ordersHandle.getNettyMessageModel().setMessage(IBMessageDefinition.AccUnKnownError.getMessage());
//                ordersHandle.response();
            } else {

                // 从内存中获取登录时存的密码
//                String pwd = RedisUtil.getString(Constant.prefix_custNo_token + custNo);


                if (passwd.equals(GlobalCache.passwd)) {

                    // 创建IB合约对象
                    Contract contract = new Contract();
                    // 合约代码
                    QuotationModel quotation = null;
                    // 港股指定为港交所，和港币
                    contract.exchange(exchange);
                    contract.currency(currency);
                    contract.secType(secType);
                    if (exchange.equals(Constant.exchange_HK)){
                        contract.localSymbol(symbol);
                    }else{
                        contract.symbol(symbol);
                    }


                    // 创建订单
                    Order order = new Order();
                    order.action(action);
                    order.orderType(orderType);
                    order.totalQuantity(Integer.parseInt(quantity));
                    order.account(account);
                    order.tif(Constant.order_tif_DAY);


                    int orderId = Main.getInstance().getController().orderId();
                    GlobalCache.ordersHandleMap.put(String.valueOf(orderId), ordersHandle);

                    OpenOrdersModel ordersModel = new OpenOrdersModel();
                    ordersModel.setCurrency(contract.currency());
                    ordersModel.setEntrust_bs(order.getAction());
                    ordersModel.setEntrust_no(String.valueOf(orderId));
                    ordersModel.setEntrust_price(Double.parseDouble(price));
                    ordersModel.setExchange_type(contract.exchange());
                    ordersModel
                            .setStock_code(contract.symbol() == null ? contract.localSymbol() : contract.symbol());
                    ordersModel.setEntrust_amount((int)order.totalQuantity());
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd  HH:mm:ss");
                    ordersModel.setExecution_time(format.format(new Date()));
                    ordersModel.setEntrust_amount_rest((int)order.totalQuantity());
                    ordersModel.setAccount(account);

//                    // 将订单信息插入数据库
//                    int result = EntrustOrdersDao.insertEntrustOrders(ordersModel);
//                    if (result != 1) {
//                        infoLogger.error("订单编号：" + orderId + ",入库失败");
//                        ordersHandle.getNettyMessageModel().setErrno(IBMessageDefinition.SystemError.getErrno());
//                        ordersHandle.getNettyMessageModel()
//                                .setMessage(IBMessageDefinition.SystemError.getMessage());
//                        ordersHandle.response();
//                    }

                    // 发送请求志ib
                    Main.getInstance().getController().placeOrModifyOrder(contract,order,new ApiController.IOrderHandler() {
                        @Override public void orderState(final OrderState orderState) {
//                            SwingUtilities.invokeLater(new Runnable() {
//                                @Override public void run() {
//                                    displayMargin( orderState);
//                                }
//                            });
                        }
                        @Override public void handle(int errorCode, final String errorMsg) {
//                            SwingUtilities.invokeLater( new Runnable() {
//                                @Override public void run() {
//                                    JOptionPane.showMessageDialog( TicketDlg.this, errorMsg);
//                                }
//                            });
                        }
                        @Override public void orderStatus(int orderId,OrderStatus status, double filled, double remaining, double avgFillPrice, long permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
                        }
                    });

                } else {
                    ordersHandle.getNettyMessageModel().setErrno(IBMessageDefinition.PwdError.getErrno());
                    ordersHandle.getNettyMessageModel().setMessage(IBMessageDefinition.PwdError.getMessage());
                    ordersHandle.response();
                }

        }

        } catch (Exception e) {
            infoLogger.error(e.getMessage(), e);
            if (ordersHandle == null) {
                ordersHandle = new OrdersHandle(channelHandlerContext, new NettyMessageModel(null));
            }
            ordersHandle.getNettyMessageModel().setErrno(IBMessageDefinition.SystemError.getErrno());
            ordersHandle.getNettyMessageModel().setMessage(IBMessageDefinition.SystemError.getMessage());
            ordersHandle.response();
        } finally {
            ordersHandle.response();
        }
    }
}
