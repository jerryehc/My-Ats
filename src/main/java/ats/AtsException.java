package ats;

/**
 * Created by Administrator on 2017-05-08.
 */
public class AtsException extends Exception{
    String msg;
    public AtsException(String errMsg){
        this.msg = errMsg;
    }
}
