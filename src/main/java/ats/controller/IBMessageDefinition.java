package ats.controller;

public class IBMessageDefinition {

    //错误码定义4开头为ruby系统程序类错误
    public static final IBMessagePair SystemError = new IBMessagePair(401, "系统异常,请稍后再试");

    //错误码定义8开头为业务类错误
    public static final IBMessagePair LoginFailError = new IBMessagePair(801, "登录失败");
    public static final IBMessagePair NoLoginError = new IBMessagePair(801, "用户未登录");
    public static final IBMessagePair NoPwdError = new IBMessagePair(804, "密码为空");

    public static final IBMessagePair AccDataError = new IBMessagePair(802, "获取的账户数据为空");
    public static final IBMessagePair OrdersDataError = new IBMessagePair(803, "获取的订单数据为空");
    public static final IBMessagePair OrdersTypeError = new IBMessagePair(804, "不支持该订单类型");
    public static final IBMessagePair AccUnKnownError = new IBMessagePair(805, "账号不存在");
    public static final IBMessagePair HandleError = new IBMessagePair(806, "操作失败");
    public static final IBMessagePair ParamError = new IBMessagePair(807, "输入参数错误");
    public static final IBMessagePair PwdError = new IBMessagePair(809, "密码错误");
    public static final IBMessagePair OrdersPendingSubmit = new IBMessagePair(810, "已经传递了订单,但还未接到来自订单目的地接收的确认信息");
    public static final IBMessagePair OrdersPendingCancel = new IBMessagePair(811, "已经发送了取消订单的请求,但还未收到来自订单目的地的取消确认.此时,你的定单没有被确认取消。在你的取消请求等待期间。你的定单仍有可能得到执行。");
    public static final IBMessagePair OrdersInactiveError = new IBMessagePair(812, "订单被拒");
    public static final IBMessagePair HandleTimeOutError = new IBMessagePair(813, "网络异常");
    public static final IBMessagePair WeekError = new IBMessagePair(814, "只支持周一至周五换汇");
    public static final IBMessagePair FundExchangeError = new IBMessagePair(815, "换汇失败");

    public static void setIbErrorMap(){
        GlobalCache.ibErrorMap.put(135, "不能找到订单的代号");
        GlobalCache.ibErrorMap.put(136, "不能取消该订单");
        GlobalCache.ibErrorMap.put(200, "暂不支持该类订单");
        GlobalCache.ibErrorMap.put(388, "订单数量不能小于最低要求");
        GlobalCache.ibErrorMap.put(461, "订单头寸错误");
    }
}
