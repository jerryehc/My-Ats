package ats.handler;

import ats.controller.Constant;
import com.ib.client.TickType;
import com.ib.client.Types;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Administrator on 2017-05-08.
 */
public class TopMktDataAdapter implements ITopMktDataHandler {
    protected final Logger logger = LogManager.getLogger(Constant.logger_market_data);
    @Override public void tickPrice(TickType tickType, double price, int canAutoExecute) {
//        logger.info("tickType:{} price:{} canAutoExecute:{}",tickType,price,canAutoExecute);
    }
    @Override public void tickSize(TickType tickType, int size) {
//        logger.info("tickType:{} size:{}",tickType,size);
    }
    @Override public void tickString(TickType tickType, String value) {
//        logger.info("tickType:{} size:{}",tickType,value);
    }
    @Override public void tickSnapshotEnd() {
//        logger.info("tickSnapshotEnd");
    }
    @Override public void marketDataType(Types.MktDataType marketDataType) {
//        logger.info("marketDataType:{} ",marketDataType);
    }
}