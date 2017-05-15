package ats.controller;



import java.util.HashMap;
import java.util.Map;

/*
 * 用于实例化全局的map
 */
public class GlobalCache {

    // 订单操作map，将订单操作handle对象放入此map中
    public static final Map<String, OrdersHandle> ordersHandleMap = new HashMap<String, OrdersHandle>();
    // 换汇操作map，将换汇操作handle对象放入此map中
    public static final Map<String, OrdersHandle> fundExchangeHandleMap = new HashMap<String, OrdersHandle>();
    // 存取操作map，将各类（除订单）操作handle对象放入此map中
    public static final Map<String, SuperHandle> handleMap = new HashMap<String, SuperHandle>();

    // 账户信息存储map,key为账户
    public static final Map<String, AccountDataSearchModel>  accountInfoMap= new HashMap<String, AccountDataSearchModel>();
    // 汇率存储
    public static final Map<Integer,Double>  exchangeRateMap= new HashMap<Integer,Double>();

    // 全局变量，用于存储ib账户列表（格式：DI359186,DU359187,DU359188,DU359189,DU359190,DU359191）
    public static String accountsListForPostion = null;
    // ib错误信息map
    public static final Map  ibErrorMap= new HashMap();

    public static final String passwd = "123";

}
