package ats.controller;

public class PlaceOrdersModel {

    private String entrust_no="";

    public String getEntrust_no() {
        return entrust_no;
    }
    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public PlaceOrdersModel(String entrust_no){
        this.entrust_no=entrust_no;
    }

    public PlaceOrdersModel(){}
}
