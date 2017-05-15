package ats;

import ats.controller.Constant;
import ats.handler.ITopMktDataHandler;
import ats.model.TopQuote;
import com.ib.client.*;
import com.ib.controller.ApiController;
import com.ib.controller.Formats;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import net.openhft.chronicle.wire.DocumentContext;
import net.openhft.chronicle.wire.Wire;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-04-03.
 */
public class Main implements ApiController.IConnectionHandler {

    private final Logger infoLogger = LogManager.getLogger(Constant.inLoggerName);

    private ApiController apiController;

    public static Main instance;


    public static void main(String[] args) {
        Main main = new Main();
        main.start();

    }

    public static Main getInstance(){
        if (instance == null){
            instance = new Main();
        }
        return instance;
    }

    public void start(){
        try {
            boolean check_flag = true;

            String strCharset = PropertiesUtil.getPropertyValue("charset", "UTF-8");
            if (null == strCharset) {
                infoLogger.error("charset配置不正确，请查看配置文件");
                check_flag = false;
            }

            String strEnv = PropertiesUtil.getPropertyValue("env", "development");
            if (null == strEnv) {
                infoLogger.error("env配置不正确，请查看配置文件");
                check_flag = false;
            }

            String configLocation = PropertiesUtil.getPropertyValue("contextConfigLocation", "web-servlet.xml");
            if (null == configLocation) {
                infoLogger.error("configLocation配置不正确，请查看配置文件");
                check_flag = false;
            }

            int iPort = PropertiesUtil.getPropertyValueInt("port", 9000);

            if (!check_flag) {
                System.exit(-1);
            }

            infoLogger.debug("connecting to {}:{},clientId:{}", Constant.twsHost ,Constant.twsPort,Constant.twsClientId_1);
            getController().connect(Constant.twsHost, Constant.twsPort, Constant.twsClientId_1, "");
            apiController.reqIds(1);



            SingleChronicleQueue queue = SingleChronicleQueueBuilder.binary(Constant.queueForOrder).build();
            ExcerptTailer tailer = queue.createTailer();

//            DumpQueueMain.dump(path);
            tailer.toEnd();


            while (true) {

                try(DocumentContext dc = tailer.readingDocument()){
                    Wire wire = dc.wire();
                    if (wire != null){
                        int action = wire.read(()-> "atsAction").int8();
                        dispatch(action,wire);
                    }
                }


            }

//            AtsServer server = new AtsServer(iPort, strCharset, configLocation.split(","));
//            server.start();
        }catch(Exception e){
            infoLogger.error(e.getMessage(),e);
        }


    }

    private void dispatch(int action,Wire wire){
        switch (action){
            case AtsAction.placeOrder:
                read_placeOrder(wire);
                break;
            case AtsAction.reqOpenOrders:
                read_reqOpenOrder(wire);
                break;
            case AtsAction.cancelOrder:
                read_cancelOrder(wire);
                break;
            case AtsAction.cancelAllOrders:
                read_cancelAllOrders();
                break;
            case AtsAction.reqMarketData:
                read_reqMktData(wire);
                break;
            case AtsAction.cancelMarketData:
                read_cancelMarketData(wire);
                break;
            default:
                break;


        }
    }

    private void read_cancelMarketData(Wire wire){
        infoLogger.trace("read_cancelMarketData");

        String symbol = wire.read(() -> "symbol").text();
        apiController.cancelTopMktData(symbol);
    }

    private void read_reqMktData(Wire wire){
        infoLogger.trace("read_reqMktData");

//        int tickerId = wire.read(()-> "tickerId").int32();

        Contract contract = new Contract();
        contract.m_exchange = wire.read(()-> "exchange").text();
        contract.m_secType = wire.read(()-> "secType").text();
        String symbol = wire.read(() -> "symbol").text();
        contract.setActualSymbol(symbol);

        contract.m_currency = wire.read(()-> "currency").text();

        String genericTicklist = wire.read(()-> "genericTicklist").text();
        boolean snapshot = wire.read(()-> "snapshot").bool();

//        ITopMktDataHandler handler = new TopMktDataAdapter();
        ITopMktDataHandler handler = new TopQuote(symbol,"");
        apiController.reqTopMktData(contract,genericTicklist,snapshot,handler);
    }

    private void read_reqOpenOrder(Wire wire){
        infoLogger.trace("read_reqOpenOrder");
        apiController.reqLiveOrders(new ApiController.ILiveOrderHandler(){
            public void openOrder(Contract contract, Order order, OrderState orderState){
                infoLogger.info("Contract:{} Order:{} OrderState:{}",contract.toString(),order.toString(),orderState.getStatus());
            }
            public void openOrderEnd(){
                infoLogger.info("openOrderEnd");
            }
            public void orderStatus(int orderId, OrderStatus status, double filled, double remaining, double avgFillPrice, long permId, int parentId, double lastFillPrice, int clientId, String whyHeld){}
            public void handle(int orderId, int errorCode, String errorMsg){}  // add permId?
        });
    }

    private void read_cancelOrder(Wire wire){
        infoLogger.trace("read_cancelOrder");
        int orderId = wire.read(()-> "orderId").int32();
        infoLogger.trace("orderId:{}",orderId);
        apiController.cancelOrder(orderId);
    }

    private void read_cancelAllOrders(){
        infoLogger.trace("read_reqGlobalCancel");
        apiController.cancelAllOrders();
    }

    private void read_placeOrder(Wire wire){
        infoLogger.trace("read_placeOrder");
        Contract contract = new Contract();
        contract.m_exchange = wire.read(()-> "exchange").text();
        contract.m_secType = wire.read(()-> "secType").text();
        contract.setActualSymbol(wire.read(() -> "symbol").text());


        contract.m_currency = wire.read(()-> "currency").text();
//        contract.m_localSymbol

        Order order = new Order();

        order.action(wire.read(()-> "action").text());
        order.totalQuantity(wire.read(()-> "totalQuantity").float64());
        order.orderType(wire.read(()-> "orderType").text());
        order.lmtPrice(wire.read(()-> "lmtPrice").float64());
        order.faProfile("profile_1");

        infoLogger.info("symbol:{} secType:{} exchange:{} currency:{} action:{} totalQuantity:{} orderType:{} lmtPrice:{}",
                contract.getActualSymbol(),
                contract.m_secType,contract.m_exchange,contract.m_currency,order.getAction(),
                order.totalQuantity(),order.orderType(),order.lmtPrice());

        apiController.placeOrModifyOrder(contract,order,new ApiController.IOrderHandler() {
            @Override public void orderState(final OrderState orderState) {
            }
            @Override public void handle(int errorCode, final String errorMsg) {
            }
            @Override public void orderStatus(int orderId,OrderStatus status, double filled, double remaining, double avgFillPrice, long permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
                infoLogger.info("orderId:" + orderId + " Status:" + status.name() + " filled:" + filled + " remaining:" + remaining + " avgFillPrice:"+avgFillPrice
                        + " permId:" + permId + " parentId:" + parentId + " lastFillPrice:" + lastFillPrice + " clientId:" + clientId + " whyHeld:" + whyHeld );
            }
        });
    }

//    public boolean isLocalSymbol(String secType, String exchange){
//        if ( ("WAR".equals(secType) || "IOPT".equals(secType))
//                && "SEHK".equals(exchange) ){
//            return true;
//        }else{
//            return false;
//        }
//    }


    private void testOrder(String stock_code){
        // 创建IB合约对象 */
//        Contract contract = new Contract();
//        // 合约代码
//        QuotationModel quotation = null;
//
//        contract.m_exchange = "SEHK";
//        contract.m_currency = "HKD";
//
//
//        // 证券类型为股票
//        contract.m_secType ="STK";
//        // 证券代码
//        switch (contract.m_symbol = stock_code) {
//        }
//
//        // 创建订单
//        Order order = new Order();
//        order.m_orderType = Constant.order_type_LMT;
//        order.m_lmtPrice = Double.parseDouble(entrust_price);
//
//        // 订单类型分为，限价单、市价单，根据客户端传入的参数判断是哪种订单类型
//        if (Constant.entrust_prop_ELO.equals(entrust_prop)) {
//            order.m_orderType = Constant.order_type_LMT;
//            order.m_lmtPrice = Double.parseDouble(entrust_price);
//        } else if (Constant.entrust_prop_ELO_P.equals(entrust_prop)
//                && exchange_type.equals(Constant.exchange_type_us)) {
//            order.m_orderType = Constant.order_type_LMT;
//            order.m_lmtPrice = Double.parseDouble(entrust_price);
//        } else if (Constant.entrust_prop_aoAtAuction.equals(entrust_prop)) {
//            order.m_orderType = Constant.order_type_MKT;
//        } else {
//            ordersHandle.getNettyMessageModel()
//                    .setErrno(IBMessageDefinition.OrdersTypeError.getErrno());
//            ordersHandle.getNettyMessageModel()
//                    .setMessage(IBMessageDefinition.OrdersTypeError.getMessage());
//            ordersHandle.response();
//        }
//
//        order.m_totalQuantity = Integer.parseInt(entrust_amount);
//        order.m_account = account;
//        order.m_tif = Constant.order_tif_DAY;
//
//        int orderId = Main.receiver.getOrderId();
//        GlobalCache.ordersHandleMap.put(String.valueOf(orderId), ordersHandle);
//
//        OpenOrdersModel ordersModel = new OpenOrdersModel();
//        ordersModel.setCurrency(contract.m_currency);
//        ordersModel.setEntrust_bs(order.m_action);
//        ordersModel.setEntrust_no(String.valueOf(orderId));
//        ordersModel.setEntrust_price(Double.parseDouble(entrust_price));
//        ordersModel.setExchange_type(contract.m_exchange);
//        ordersModel
//                .setStock_code(contract.m_symbol == null ? contract.m_localSymbol : contract.m_symbol);
//        ordersModel.setEntrust_amount(order.m_totalQuantity);
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd  HH:mm:ss");
//        ordersModel.setExecution_time(format.format(new Date()));
//        ordersModel.setEntrust_amount_rest(order.m_totalQuantity);
//        ordersModel.setAccount(account);
//
//        // 将订单信息插入数据库
//        int result = EntrustOrdersDao.insertEntrustOrders(ordersModel);
//        if (result != 1) {
//            infoLogger.error("订单编号：" + orderId + ",入库失败");
//            ordersHandle.getNettyMessageModel().setErrno(IBMessageDefinition.SystemError.getErrno());
//            ordersHandle.getNettyMessageModel()
//                    .setMessage(IBMessageDefinition.SystemError.getMessage());
//            ordersHandle.response();
//        }
//
//        // 发送请求志ib
//        Main.sender.placeOrder(orderId, contract, order);
    }

    public ApiController getController() {
        if ( apiController == null ) {
            apiController = new ApiController( this );

        }
        return apiController;
    }


    @Override public void connected() {
        show( "connected");

        getController().reqCurrentTime( new ApiController.ITimeHandler() {
            @Override public void currentTime(long time) {
                show( "Server date/time is " + Formats.fmtDate(time * 1000) );
            }
        });

        getController().reqBulletins( true, new ApiController.IBulletinHandler() {
            @Override public void bulletin(int msgId, Types.NewsType newsType, String message, String exchange) {
                String str = String.format( "Received bulletin:  type=%s  exchange=%s", newsType, exchange);
                show( str);
                show( message);
            }
        });
    }

    @Override public void disconnected() {
        show( "disconnected");
//        m_connectionPanel.m_status.setText( "disconnected");
    }

    @Override public void accountList(ArrayList<String> list) {
        show( "Received account list");
//        m_acctList.clear();
//        m_acctList.addAll( list);
    }

    @Override public void show( final String str) {

    }

    @Override public void error(Exception e) {
        show( e.toString() );
    }

    @Override public void message(int id, int errorCode, String errorMsg) {
        show( id + " " + errorCode + " " + errorMsg);
    }
}
