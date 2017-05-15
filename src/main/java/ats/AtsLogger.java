package ats;

import com.ib.controller.ApiConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Administrator on 2017-04-06.
 */
public class AtsLogger implements ApiConnection.ILogger {
    private static final Logger trading_logger = LogManager.getLogger("trading_logger");
    @Override
    public void log(final String str) {
        //@// TODO: 2017-04-05
//        trading_logger.traceEntry();
        trading_logger.trace(str);
//        trading_logger.traceExit();
    }
}
