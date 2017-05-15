package ats.controller;


import java.io.Serializable;

public class ExecutionOrdersModel implements Serializable {

    private String execution_time="";  //执行时间
    private String stock_code="";  //证券代码
    private double business_price=0.0; // 成交价格
    private int business_amount=0; // 成交数量
    private String exchange_type="";  //交易所类型
    private String entrust_bs=""; // 操作类型（买、卖）
    private int entrust_no=0; // 订单编号
    private String currency="";     //货币
    private String account="";  //账户
    private String startDateTimeK="";   //查询条件的开始时间(港股)
    private String endDateTimeK="";    //查询条件的结束时间(港股)
    private String startDateTimeP="";   //查询条件的开始时间(美股)
    private String endDateTimeP="";    //查询条件的结束时间(美股)
    private double entrust_price=0.0; // 委托价格
    private double entrust_amount=0; //委托数量


    public double getEntrust_price() {
        return entrust_price;
    }
    public void setEntrust_price(double entrust_price) {
        this.entrust_price = entrust_price;
    }
    public double getEntrust_amount() {
        return entrust_amount;
    }
    public void setEntrust_amount(double entrust_amount) {
        this.entrust_amount = entrust_amount;
    }
    public String getStartDateTimeK() {
        return startDateTimeK;
    }
    public void setStartDateTimeK(String startDateTimeK) {
        this.startDateTimeK = startDateTimeK;
    }
    public String getEndDateTimeK() {
        return endDateTimeK;
    }
    public void setEndDateTimeK(String endDateTimeK) {
        this.endDateTimeK = endDateTimeK;
    }
    public String getStartDateTimeP() {
        return startDateTimeP;
    }
    public void setStartDateTimeP(String startDateTimeP) {
        this.startDateTimeP = startDateTimeP;
    }
    public String getEndDateTimeP() {
        return endDateTimeP;
    }
    public void setEndDateTimeP(String endDateTimeP) {
        this.endDateTimeP = endDateTimeP;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getExecution_time() {
        return execution_time;
    }
    public void setExecution_time(String execution_time) {
        this.execution_time = execution_time;
    }
    public String getStock_code() {
        return stock_code;
    }
    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
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
    public int getEntrust_no() {
        return entrust_no;
    }
    public void setEntrust_no(int entrust_no) {
        this.entrust_no = entrust_no;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
