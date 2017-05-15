package ats.message;

import com.ib.client.Contract;
import com.ib.client.Order;

/**
 * Created by Administrator on 2017-04-20.
 */
public class OrderMessage {
    private Order order;
    private Contract contract;
    private int orderId;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
