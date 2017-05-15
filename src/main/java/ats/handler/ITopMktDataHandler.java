package ats.handler;

import com.ib.client.TickType;
import com.ib.client.Types;

/**
 * Created by Administrator on 2017-05-08.
 */
public interface ITopMktDataHandler {
    void tickPrice(TickType tickType, double price, int canAutoExecute);
    void tickSize(TickType tickType, int size);
    void tickString(TickType tickType, String value);
    void tickSnapshotEnd();
    void marketDataType(Types.MktDataType marketDataType);
}