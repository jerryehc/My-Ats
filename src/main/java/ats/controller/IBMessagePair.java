package ats.controller;



/**
 * 消息对，包括消息码和消息
 *
 * @author chenlejia
 *
 */
public class IBMessagePair {

    // 消息码
    private String errno;
    // 消息
    private String message;

    /**
     * 缺省构造函数
     */
    public IBMessagePair() {
    }

    /**
     * 构造函数
     *
     * @param msgCode
     *            消息码
     * @param errString消息
     */
    public IBMessagePair(int msgNo, String message) {
        this.errno = String.valueOf(msgNo);
        this.message = message;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CodeMsgPair [errno=" + errno + ", message=" + message + "]";
    }
}