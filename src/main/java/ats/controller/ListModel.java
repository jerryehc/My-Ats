package ats.controller;



import java.util.ArrayList;
import java.util.List;

public class ListModel {

    private String account="";
    private List list=new ArrayList();;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
