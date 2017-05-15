package ats.message;

import ats.Main;
import com.ib.client.OrderState;
import com.ib.client.OrderStatus;
import com.ib.controller.ApiController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Administrator on 2017-04-19.
 */
public class OrderConsumer {
    private final Logger infoLogger = LogManager.getLogger("infoLogger");

    public void onMessage(OrderMessage orderMessage){
        Main.getInstance().getController().placeOrModifyOrder(orderMessage.getContract(),orderMessage.getOrder(),new ApiController.IOrderHandler() {
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
                infoLogger.info("Status:" + status.name() + " filled:" + filled + " remaining:" + remaining + " avgFillPrice:"+avgFillPrice
                    + " permId:" + permId + " parentId:" + parentId + " lastFillPrice:" + lastFillPrice + " clientId:" + clientId + " whyHeld:" + whyHeld );
            }
        });

    }
}
