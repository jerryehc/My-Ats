package ats.model;


import ats.handler.TopMktDataAdapter;
import com.ib.client.TickType;
import com.ib.client.Types.MktDataType;

import static com.ib.controller.Formats.fmtPct;

public class TopQuote extends TopMktDataAdapter {
//    private final Logger logger = LogManager.getLogger(Constant.logger_market_data);

    String symbol;
    String description;
    double bid;
    double ask;
    double last;
    long lastTime;
    int bidSize;
    int askSize;
    double close;
    int volume;
    boolean frozen;

    public TopQuote(String symbol,String description) {
        this.symbol = symbol;
        this.description = description;
    }

//    TopQuote(AbstractTableModel model, String description) {
//        m_model = model;
//        description = description;
//    }

    public String change() {
        return close == 0	? null : fmtPct( (last - close) / close);
    }

    @Override public void tickPrice(TickType tickType, double price, int canAutoExecute) {
        switch( tickType) {
            case BID:
                bid = price;
                break;
            case ASK:
                ask = price;
                break;
            case LAST:
                last = price;
                break;
            case CLOSE:
                close = price;
                break;
            default: break;
        }
        logger.info("{} {} {}",tickType,price,canAutoExecute);
//        m_model.fireTableDataChanged(); // should use a timer to be more efficient
    }

    @Override public void tickSize( TickType tickType, int size) {
        switch( tickType) {
            case BID_SIZE:
                bidSize = size;
                break;
            case ASK_SIZE:
                askSize = size;
                break;
            case VOLUME:
                volume = size;
                break;
            default: break;
        }
        logger.info("{} {}",tickType,size);
//        m_model.fireTableDataChanged();
    }

    @Override public void tickString(TickType tickType, String value) {
        switch( tickType) {
            case LAST_TIMESTAMP:
                lastTime = Long.parseLong( value) * 1000;
                break;
            default: break;
        }
        logger.info("{} {}",tickType,value);
    }

    @Override public void marketDataType(MktDataType marketDataType) {
        frozen = marketDataType == MktDataType.Frozen;
//        m_model.fireTableDataChanged();
    }
}