package ats.controller;

import java.io.Serializable;

/**
 * <code>QuotationVO<code> 实时行情
 */
public class QuotationModel implements Serializable {

    private static final long serialVersionUID = 7184505879193249169L;

    /** 资产ID */
    private String assetId;
    /** 日期 */
    private String date;
    /** 时间 */
    private String time;
    /** 名称 */
    private String stkName;
    /** 代码 */
    private String stkCode;
    /** 公司代码 */
    private int corpCode;
    /** 最新价 */
    private double price;
    /** 昨收 */
    private double prevClose;
    /** 今开 */
    private double open;
    /** 最高 */
    private double high;
    /** 最低 */
    private double low;
    /** 交易总额 */
    private double turnOver;
    /** 交易总量 */
    private double totalVolume;
    /** 上一分钟交易总量 */
    private double lastVol = 0;
    /** 上一分钟交易总额 */
    private double lastTurnover = 0;
    /** 均价 */
    private double avgPrice;
    /** 涨跌幅 */
    private double changePct;
    /** 涨跌额 */
    private double change;
    /** 每手股数 */
    private int lotSize;
    /** 详细类型 101:股票 */
    private int secSType;
    /** 港股大类:1.股票 2.债券 3.基金 4.权证 5.指数 6.回购 */
    /** 美股大类:1.股票 2.基金  5.指数 6.回购 */
    private int secType;
    /** 港股细分交易市场 */
    private String subMarket;
    /** 判断市场状态 false.收市状态，true.开市状态 */
    private boolean isOpenMkt = true;
    /** 更新时间 */
    private long updateTime;//
    /** 港股从行情数据无法获取更多状态，只有0 - 正常, 3 - 停牌，4 - IPO期间 */
    private int status;
    /** 货币 */
    private String currency;
    /* 十档委托买量 */
    private long b1;
    private long b2;
    private long b3;
    private long b4;
    private long b5;
    private long b6;
    private long b7;
    private long b8;
    private long b9;
    private long b10;
    /* 十档委买价格 */
    private double b1Price;
    private double b2Price;
    private double b3Price;
    private double b4Price;
    private double b5Price;
    private double b6Price;
    private double b7Price;
    private double b8Price;
    private double b9Price;
    private double b10Price;
    /* 十档委托卖量 */
    private long s1;
    private long s2;
    private long s3;
    private long s4;
    private long s5;
    private long s6;
    private long s7;
    private long s8;
    private long s9;
    private long s10;
    /* 十档委托卖价格 */
    private double s1Price;
    private double s2Price;
    private double s3Price;
    private double s4Price;
    private double s5Price;
    private double s6Price;
    private double s7Price;
    private double s8Price;
    private double s9Price;
    private double s10Price;

    /** 指数交易总额 */
    private double indTurnOver;
    /** 指数交易总量 */
    private double indTotalVolume;

    private long lastTradeVolume;//每首成交量
    private String changeSymbol; //资金流向

    private double littleIn;//小单流入
    private double middleIn;//中单流入
    private double bigIn;//大单流入
    private double hugeIn;//超大单流入
    private double littleOut;//小单流出
    private double middleOut;//中单流出
    private double bigOut;//大单流出
    private double hugeOut;//超大单流出
    private long totalTransaction;//总成交笔数
    private double referenceQuantity;//参考量:前30天平均每笔成交量
    private int boardCode;//板块
    private String type; //K:港股；P:美股
    private double buyInitMargin;
    private double buyMaintMargin;
    private double sellInitMargin;
    private double sellMaintMargin;

    public double getSellInitMargin() {
        return sellInitMargin;
    }

    public void setSellInitMargin(double sellInitMargin) {
        this.sellInitMargin = sellInitMargin;
    }

    public double getSellMaintMargin() {
        return sellMaintMargin;
    }

    public void setSellMaintMargin(double sellMaintMargin) {
        this.sellMaintMargin = sellMaintMargin;
    }

    public double getBuyInitMargin() {
        return buyInitMargin;
    }

    public void setBuyInitMargin(double buyInitMargin) {
        this.buyInitMargin = buyInitMargin;
    }

    public double getBuyMaintMargin() {
        return buyMaintMargin;
    }

    public void setBuyMaintMargin(double buyMaintMargin) {
        this.buyMaintMargin = buyMaintMargin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStkName() {
        return stkName;
    }

    public void setStkName(String stkName) {
        this.stkName = stkName;
    }

    public String getStkCode() {
        return stkCode;
    }

    public void setStkCode(String stkCode) {
        this.stkCode = stkCode;
    }

    public int getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(int corpCode) {
        this.corpCode = corpCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(double prevClose) {
        this.prevClose = prevClose;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(double turnOver) {
        this.turnOver = turnOver;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public double getLastVol() {
        return lastVol;
    }

    public void setLastVol(double lastVol) {
        this.lastVol = lastVol;
    }

    public double getLastTurnover() {
        return lastTurnover;
    }

    public void setLastTurnover(double lastTurnover) {
        this.lastTurnover = lastTurnover;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public double getChangePct() {
        return changePct;
    }

    public void setChangePct(double changePct) {
        this.changePct = changePct;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    public int getSecSType() {
        return secSType;
    }

    public void setSecSType(int secSType) {
        this.secSType = secSType;
    }

    public int getSecType() {
        return secType;
    }

    public void setSecType(int secType) {
        this.secType = secType;
    }

    public boolean isOpenMkt() {
        return isOpenMkt;
    }

    public void setOpenMkt(boolean isOpenMkt) {
        this.isOpenMkt = isOpenMkt;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSubMarket() {
        return subMarket;
    }

    public void setSubMarket(String subMarket) {
        this.subMarket = subMarket;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getB1() {
        return b1;
    }

    public void setB1(long b1) {
        this.b1 = b1;
    }

    public long getB2() {
        return b2;
    }

    public void setB2(long b2) {
        this.b2 = b2;
    }

    public long getB3() {
        return b3;
    }

    public void setB3(long b3) {
        this.b3 = b3;
    }

    public long getB4() {
        return b4;
    }

    public void setB4(long b4) {
        this.b4 = b4;
    }

    public long getB5() {
        return b5;
    }

    public void setB5(long b5) {
        this.b5 = b5;
    }

    public long getB6() {
        return b6;
    }

    public void setB6(long b6) {
        this.b6 = b6;
    }

    public long getB7() {
        return b7;
    }

    public void setB7(long b7) {
        this.b7 = b7;
    }

    public long getB8() {
        return b8;
    }

    public void setB8(long b8) {
        this.b8 = b8;
    }

    public long getB9() {
        return b9;
    }

    public void setB9(long b9) {
        this.b9 = b9;
    }

    public long getB10() {
        return b10;
    }

    public void setB10(long b10) {
        this.b10 = b10;
    }

    public double getB1Price() {
        return b1Price;
    }

    public void setB1Price(double b1Price) {
        this.b1Price = b1Price;
    }

    public double getB2Price() {
        return b2Price;
    }

    public void setB2Price(double b2Price) {
        this.b2Price = b2Price;
    }

    public double getB3Price() {
        return b3Price;
    }

    public void setB3Price(double b3Price) {
        this.b3Price = b3Price;
    }

    public double getB4Price() {
        return b4Price;
    }

    public void setB4Price(double b4Price) {
        this.b4Price = b4Price;
    }

    public double getB5Price() {
        return b5Price;
    }

    public void setB5Price(double b5Price) {
        this.b5Price = b5Price;
    }

    public double getB6Price() {
        return b6Price;
    }

    public void setB6Price(double b6Price) {
        this.b6Price = b6Price;
    }

    public double getB7Price() {
        return b7Price;
    }

    public void setB7Price(double b7Price) {
        this.b7Price = b7Price;
    }

    public double getB8Price() {
        return b8Price;
    }

    public void setB8Price(double b8Price) {
        this.b8Price = b8Price;
    }

    public double getB9Price() {
        return b9Price;
    }

    public void setB9Price(double b9Price) {
        this.b9Price = b9Price;
    }

    public double getB10Price() {
        return b10Price;
    }

    public void setB10Price(double b10Price) {
        this.b10Price = b10Price;
    }

    public long getS1() {
        return s1;
    }

    public void setS1(long s1) {
        this.s1 = s1;
    }

    public long getS2() {
        return s2;
    }

    public void setS2(long s2) {
        this.s2 = s2;
    }

    public long getS3() {
        return s3;
    }

    public void setS3(long s3) {
        this.s3 = s3;
    }

    public long getS4() {
        return s4;
    }

    public void setS4(long s4) {
        this.s4 = s4;
    }

    public long getS5() {
        return s5;
    }

    public void setS5(long s5) {
        this.s5 = s5;
    }

    public long getS6() {
        return s6;
    }

    public void setS6(long s6) {
        this.s6 = s6;
    }

    public long getS7() {
        return s7;
    }

    public void setS7(long s7) {
        this.s7 = s7;
    }

    public long getS8() {
        return s8;
    }

    public void setS8(long s8) {
        this.s8 = s8;
    }

    public long getS9() {
        return s9;
    }

    public void setS9(long s9) {
        this.s9 = s9;
    }

    public long getS10() {
        return s10;
    }

    public void setS10(long s10) {
        this.s10 = s10;
    }

    public double getS1Price() {
        return s1Price;
    }

    public void setS1Price(double s1Price) {
        this.s1Price = s1Price;
    }

    public double getS2Price() {
        return s2Price;
    }

    public void setS2Price(double s2Price) {
        this.s2Price = s2Price;
    }

    public double getS3Price() {
        return s3Price;
    }

    public void setS3Price(double s3Price) {
        this.s3Price = s3Price;
    }

    public double getS4Price() {
        return s4Price;
    }

    public void setS4Price(double s4Price) {
        this.s4Price = s4Price;
    }

    public double getS5Price() {
        return s5Price;
    }

    public void setS5Price(double s5Price) {
        this.s5Price = s5Price;
    }

    public double getS6Price() {
        return s6Price;
    }

    public void setS6Price(double s6Price) {
        this.s6Price = s6Price;
    }

    public double getS7Price() {
        return s7Price;
    }

    public void setS7Price(double s7Price) {
        this.s7Price = s7Price;
    }

    public double getS8Price() {
        return s8Price;
    }

    public void setS8Price(double s8Price) {
        this.s8Price = s8Price;
    }

    public double getS9Price() {
        return s9Price;
    }

    public void setS9Price(double s9Price) {
        this.s9Price = s9Price;
    }

    public double getS10Price() {
        return s10Price;
    }

    public void setS10Price(double s10Price) {
        this.s10Price = s10Price;
    }

    public double getIndTurnOver() {
        return indTurnOver;
    }

    public void setIndTurnOver(double indTurnOver) {
        this.indTurnOver = indTurnOver;
    }

    public double getIndTotalVolume() {
        return indTotalVolume;
    }

    public void setIndTotalVolume(double indTotalVolume) {
        this.indTotalVolume = indTotalVolume;
    }

    public long getLastTradeVolume() {
        return lastTradeVolume;
    }

    public void setLastTradeVolume(long lastTradeVolume) {
        this.lastTradeVolume = lastTradeVolume;
    }

    public String getChangeSymbol() {
        return changeSymbol;
    }

    public void setChangeSymbol(String changeSymbol) {
        this.changeSymbol = changeSymbol;
    }

    public double getLittleIn() {
        return littleIn;
    }

    public void setLittleIn(double littleIn) {
        this.littleIn = littleIn;
    }

    public double getMiddleIn() {
        return middleIn;
    }

    public void setMiddleIn(double middleIn) {
        this.middleIn = middleIn;
    }

    public double getBigIn() {
        return bigIn;
    }

    public void setBigIn(double bigIn) {
        this.bigIn = bigIn;
    }

    public double getHugeIn() {
        return hugeIn;
    }

    public void setHugeIn(double hugeIn) {
        this.hugeIn = hugeIn;
    }

    public double getLittleOut() {
        return littleOut;
    }

    public void setLittleOut(double littleOut) {
        this.littleOut = littleOut;
    }

    public double getMiddleOut() {
        return middleOut;
    }

    public void setMiddleOut(double middleOut) {
        this.middleOut = middleOut;
    }

    public double getBigOut() {
        return bigOut;
    }

    public void setBigOut(double bigOut) {
        this.bigOut = bigOut;
    }

    public double getHugeOut() {
        return hugeOut;
    }

    public void setHugeOut(double hugeOut) {
        this.hugeOut = hugeOut;
    }

    public long getTotalTransaction() {
        return totalTransaction;
    }

    public void setTotalTransaction(long totalTransaction) {
        this.totalTransaction = totalTransaction;
    }

    public double getReferenceQuantity() {
        return referenceQuantity;
    }

    public void setReferenceQuantity(double referenceQuantity) {
        this.referenceQuantity = referenceQuantity;
    }

    public int getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(int boardCode) {
        this.boardCode = boardCode;
    }

}
