import ats.AtsAction;
import ats.controller.Constant;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import net.openhft.chronicle.wire.DocumentContext;
import net.openhft.chronicle.wire.Wire;

import static ats.AtsAction.cancelAllOrders;

/**
 * Created by Administrator on 2017-04-20.
 */
public class TestOrder {
    public static void main(String[] args) {
        String path = Constant.queueForOrder;
        SingleChronicleQueue queue = SingleChronicleQueueBuilder.binary(path).build();

        ExcerptAppender appender = queue.acquireAppender();
//
//        placeOrder(appender,"67814","IOPT","SEHK","HKD","BUY",10000,0.039);
//        placeOrder(appender,"5","STK","SEHK","HKD","BUY",400,67.5);
//        placeOrder(appender,"14332","WAR","SEHK","HKD","BUY",10000,0.38);
//        reqOpenOrder(appender);
//        cancelOrder(appender,16);
//        cancelAllOrders(appender);
        cancelMarketData(appender,"EUR");
//        reqMarketData(appender,"EUR","CASH","IDEALPRO","USD","",false);


    }

    public static void placeOrder(ExcerptAppender appender,String symbol,String secType,String exchange,String currency,String action,
                                   double totalQuantity,double price){
        System.out.println("placeOrder");
        for(int i = 0; i < 1;i++){
            try (DocumentContext dc = appender.writingDocument()) {
                Wire wire = dc.wire();
                wire.write(() -> "atsAction").int8(AtsAction.placeOrder);

                wire.write(() -> "exchange").text(exchange);
                wire.write(() -> "secType").text(secType);

                wire.write(() -> "symbol").text(symbol);
                wire.write(() -> "currency").text(currency);
                wire.write(() -> "action").text(action);
                wire.write(() -> "totalQuantity").float64(totalQuantity);
                wire.write(() -> "orderType").text("LMT");
                wire.write(() -> "lmtPrice").float64(price);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
//int tickerId, Contract contract, String genericTicklist, boolean snapshot
    public static void reqMarketData(ExcerptAppender appender,String symbol,String secType,String exchange,String currency,
                                     String genericTicklist,boolean snapshot){
        System.out.println("reqMarketData");

            try (DocumentContext dc = appender.writingDocument()) {
                Wire wire = dc.wire();
                wire.write(() -> "atsAction").int8(AtsAction.reqMarketData);

//                wire.write(() -> "tickerId").int32(tickerId);
                wire.write(() -> "exchange").text(exchange);
                wire.write(() -> "secType").text(secType);

                wire.write(() -> "symbol").text(symbol);
                wire.write(() -> "currency").text(currency);
                wire.write(() -> "genericTicklist").text(genericTicklist);
                wire.write(() -> "snapshot").bool(Boolean.valueOf(snapshot));

            }catch(Exception e){
                e.printStackTrace();
            }

    }

    public static void cancelMarketData(ExcerptAppender appender,String symbol){
        System.out.println("cancelMarketData");

        try (DocumentContext dc = appender.writingDocument()) {
            Wire wire = dc.wire();
            wire.write(() -> "atsAction").int8(AtsAction.cancelMarketData);
            wire.write(() -> "symbol").text(symbol);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void reqOpenOrder(ExcerptAppender appender){
        System.out.println("reqOpenOrder");
        try (DocumentContext dc = appender.writingDocument()) {
            Wire wire = dc.wire();
            wire.write(() -> "atsAction").int8(AtsAction.reqOpenOrders);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void cancelOrder(ExcerptAppender appender,int orderId){
        System.out.println("cancelOrder");
        try (DocumentContext dc = appender.writingDocument()) {
            Wire wire = dc.wire();
            wire.write(() -> "atsAction").int8(AtsAction.cancelOrder);
            wire.write(() -> "orderId").int32(orderId);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void cancelAllOrders(ExcerptAppender appender){
        System.out.println("cancelAllOrders");
        try (DocumentContext dc = appender.writingDocument()) {
            Wire wire = dc.wire();
            wire.write(() -> "atsAction").int8(cancelAllOrders);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
