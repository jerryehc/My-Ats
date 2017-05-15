package ats.controller;


public class Constant {
    public static final String twsHost = "127.0.0.1";
    public static final int twsPort = 7496;
    public static final int twsClientId_Master = 0;
    public static final int twsClientId_1 = 1;

    public static final int twsReconnectInterval = 3000;

    public static final String queueForOrder = "orderQueue";

    public static final String inLoggerName = "infoLogger";
    public static final String outLoggerName = "infoLogger";
    public static final String logger_market_data = "infoLogger";
//
//    public static final String print_type_request_app = "Request app";
//    public static final String print_type_response_app = "Response app";
//    public static final String print_type_request_ib = "Request ib";


     // 交易所
    public static final String exchange_HK = "SEHK"; // 港股
    public static final String exchange_us = "ISLAND"; // 美股


    // 委托买卖类别
    public static final String entrust_bs_buy = "BUY";// 买入
    public static final String entrust_bs_sell = "SELL";// 卖出

    // 委托属性
//    public static final String entrust_prop_ELO = "e";// 限价单
//    public static final String entrust_prop_ELO_P = "0";// 美股限价单
//    public static final String entrust_prop_aoAtAuction = "mk";// 市价单

    // redis键类型
    public static final String key_openorder_hk="openOrdersHK:";  //未港股成交订单信息存储key
    public static final String key_openorder_smart="openOrdersSmart:";  //未美股成交订单信息存储key
    public static final String key_openorder_account = "orderAcc:";//存储订单与账户对应关系
    public static final String key_positions="position:";  //持仓订单信息存储key
    public static final String prefix_custNo_token = "custToken:";//客户号对应的TOKEN


    // 证券类型
    public static final String stock_type_STK="STK";  //股票
    public static final String stock_type_WAR="WAR";  //认股证
    public static final String stock_type_IOPT="IOPT";  //牛熊证

    // 订单类型
    public static final String order_type_LMT="LMT";  //限价单
    public static final String order_type_MKT="MKT";  //市价单

    // 订单有效期
    public static final String order_tif_DAY="DAY";  //天

    public static final String OrderStatusPendingSubmit="PendingSubmit";   //表示你已经传递了定单， 但还未接到来自定单目的地接收的确认信息
    public static final String OrderStatusPendingCancel="PendingCancel";   //表示你已经发送了取消定单的请求， 但还未收到来自定单目的地的取消确认。
    public static final String OrderStatusSubmitted="Submitted";   //表示你的定单已经被定单目的地接受
    public static final String OrderStatusPreSubmitted="PreSubmitted";   //表示模拟定单类型已经被IB系统接收， 但还未被选中。
    public static final String OrderStatusCancelled="Cancelled";   //表示你定单的剩余部分已被IB系统确认取消了
    public static final String OrderStatusFilled="Filled";   //表示定单已被全部执行
    public static final String OrderStatusInactive="Inactive";   //表示定单已被系统接收( 模拟定单) 或交易所接收(本 地定单) ， 但由于系统、交易所或其它原因， 目前定单处于非工作状态。

    public static final String fundExchangeUSD="1";   //换汇源币种(usd)
    public static final String fundExchangeHDK="2";   //换汇源币种(hkd)

    public static final int reqAccountSummary1=1;   //订阅账户信息请求1
    public static final int reqAccountSummary2=2;   //订阅账户信息请求2
    public static final int reqAccountSummary3=3;   //订阅账户信息请求3
    public static final int reqAccountSummary4=4;   //订阅账户信息请求4

    public static final String quotationHK="hk_ts_data";  //港股行情redis key
    public static final String quotationUS="us_ts_data";  //美股行情redis key

    public static final int tickBuyPrice=1;  //买价
    public static final int tickSellPrice=2;  //卖价
    public static final int tickHighPrice=6;  //最高价
    public static final int tickLowerPrice=7;  //最低价
    public static final int tickClosePrice=9;  //收盘价

    public static final String weekSunDay="星期日";
    public static final String weekMonDay="星期一";
    public static final String weekTuesday="星期二";
    public static final String weekWednesday="星期三";
    public static final String weekThursday="星期四";
    public static final String weekFriday="星期五";
    public static final String weekSaturday="星期六";
}
