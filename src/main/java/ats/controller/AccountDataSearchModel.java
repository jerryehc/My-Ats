package ats.controller;


public class AccountDataSearchModel {

    private String account="";  //账号
    private String buying_power="0"; // 购买力
    private String available_funds="0";  //可用资金
    private String net_liquidation="0";  //净资产
    private String total_cash_value="0"; //现金余额(汇总)
    private String hkd_cash_value="0"; //现金余额(港币)
    private String usd_cash_value="0"; //现金余额(美元)
    private String look_ahead_init_margin="0";     //隔夜初始保证金
    private String excess_liquidity="0";  //剩余流动性
    private String stock_market_value="0";  //市值
    private String money_type="";   //货币种类
    private String unrealized_pnl="0"; //未实现盈亏
    private String init_margin="0"; //初始保证金
    private String maint_margin="0"; //维持保证金
    private String exchange_rate_hk="0" ;//汇率(港币兑账户基础货币)
    private String exchange_rate_us="0" ;//汇率(美元兑基础货币)

    // fieldSum标识结构实体中字段的总数，fieldCurrentSum标识结构实体中已被赋值的字段数量，
    // 当fieldSum=fieldCurrentSum时表示实体对象的字段都已赋值，此需求源于IB，
    // 因为IB每次只返回一个字段的值
//	private Integer fieldSum = new Integer(4);
//	private Integer fieldCurrentCount = new Integer(0);

//	public Integer getFieldSum() {
//		return fieldSum;
//	}
//
//	public void setFieldSum(Integer fieldSum) {
//		this.fieldSum = fieldSum;
//	}
//
//	public Integer getFieldCurrentCount() {
//		return fieldCurrentCount;
//	}
//
//	public void setFieldCurrentCount(Integer fieldCurrentCount) {
//		this.fieldCurrentCount = fieldCurrentCount;
//	}

    public String getBuying_power() {
        return buying_power;
    }

    public String getExchange_rate_hk() {
        return exchange_rate_hk;
    }

    public void setExchange_rate_hk(String exchange_rate_hk) {
        this.exchange_rate_hk = exchange_rate_hk;
    }

    public String getExchange_rate_us() {
        return exchange_rate_us;
    }

    public void setExchange_rate_us(String exchange_rate_us) {
        this.exchange_rate_us = exchange_rate_us;
    }

    public String getInit_margin() {
        return init_margin;
    }

    public void setInit_margin(String init_margin) {
        this.init_margin = init_margin;
    }

    public String getMaint_margin() {
        return maint_margin;
    }

    public void setMaint_margin(String maint_margin) {
        this.maint_margin = maint_margin;
    }

    public String getHkd_cash_value() {
        return hkd_cash_value;
    }

    public void setHkd_cash_value(String hkd_cash_value) {
        this.hkd_cash_value = hkd_cash_value;
    }

    public String getUsd_cash_value() {
        return usd_cash_value;
    }

    public void setUsd_cash_value(String usd_cash_value) {
        this.usd_cash_value = usd_cash_value;
    }

    public void setBuying_power(String buying_power) {
        this.buying_power = buying_power;
//		fieldCurrentCount += 1;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvailable_funds() {
        return available_funds;
    }

    public void setAvailable_funds(String available_funds) {
        this.available_funds = available_funds;
    }

    public String getNet_liquidation() {
        return net_liquidation;
    }

    public void setNet_liquidation(String net_liquidation) {
        this.net_liquidation = net_liquidation;
    }

    public String getTotal_cash_value() {
        return total_cash_value;
    }

    public void setTotal_cash_value(String total_cash_value) {
        this.total_cash_value = total_cash_value;
    }

    public String getStock_market_value() {
        return stock_market_value;
    }

    public void setStock_market_value(String stock_market_value) {
        this.stock_market_value = stock_market_value;
    }

    public String getLook_ahead_init_margin() {
        return look_ahead_init_margin;
    }

    public void setLook_ahead_init_margin(String look_ahead_init_margin) {
        this.look_ahead_init_margin = look_ahead_init_margin;
    }

    public String getExcess_liquidity() {
        return excess_liquidity;
    }

    public void setExcess_liquidity(String excess_liquidity) {
        this.excess_liquidity = excess_liquidity;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public String getUnrealized_pnl() {
        return unrealized_pnl;
    }

    public void setUnrealized_pnl(String unrealized_pnl) {
        this.unrealized_pnl = unrealized_pnl;
    }
}
