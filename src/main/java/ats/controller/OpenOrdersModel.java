package ats.controller;

import java.io.Serializable;

public class OpenOrdersModel implements Serializable {

    private String account;   //账户
    private String stock_code="";  //证券代码
    private double entrust_price=0.0; // 委托价格
    private int entrust_amount=0; // 下订单数量
    private int entrust_amount_rest=0; // 剩余订单数量
    private String exchange_type="";  //交易所类型
    private String entrust_bs=""; // 操作类型（买、卖）
    private String entrust_no=""; // 订单编号
    private String currency="";     //货币
    private String execution_time="";      //下单时间
    private String status="";    //订单状态
    private double business_price=0.0; //成交价
    private int business_amount=0; //成交数量
    private String startDateTime="";   //查询条件的开始时间
    private String endDateTime="";    //查询条件的结束时间
    private String entrustStatusFilled="";  //委托状态filled
    private String entrustStatusCanceled="";  //委托状态Canceled

    public String getEntrustStatusFilled() {
        return entrustStatusFilled;
    }
    public void setEntrustStatusFilled(String entrustStatusFilled) {
        this.entrustStatusFilled = entrustStatusFilled;
    }
    public String getEntrustStatusCanceled() {
        return entrustStatusCanceled;
    }
    public void setEntrustStatusCanceled(String entrustStatusCanceled) {
        this.entrustStatusCanceled = entrustStatusCanceled;
    }
    public int getEntrust_amount() {
        return entrust_amount;
    }
    public void setEntrust_amount(int entrust_amount) {
        this.entrust_amount = entrust_amount;
    }
    public double getBusiness_price() {
        return business_price;
    }
    public void setBusiness_price(double business_price) {
        this.business_price = business_price;
    }
    public int getBusiness_amount() {
        return business_amount;
    }
    public void setBusiness_amount(int business_amount) {
        this.business_amount = business_amount;
    }
    public String getStock_code() {
        return stock_code;
    }
    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }
    public double getEntrust_price() {
        return entrust_price;
    }
    public void setEntrust_price(double entrust_price) {
        this.entrust_price = entrust_price;
    }
    public String getExchange_type() {
        return exchange_type;
    }
    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }
    public String getEntrust_bs() {
        return entrust_bs;
    }
    public void setEntrust_bs(String entrust_bs) {
        this.entrust_bs = entrust_bs;
    }
    public String getEntrust_no() {
        return entrust_no;
    }
    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getExecution_time() {
        return execution_time;
    }
    public void setExecution_time(String execution_time) {
        this.execution_time = execution_time;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getEntrust_amount_rest() {
        return entrust_amount_rest;
    }
    public void setEntrust_amount_rest(int entrust_amount_rest) {
        this.entrust_amount_rest = entrust_amount_rest;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }
    public String getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
}
