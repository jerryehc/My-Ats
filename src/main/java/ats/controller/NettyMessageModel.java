package ats.controller;

public class NettyMessageModel {

    private String errno = "0";// 响应码
    private String message = "成功";// 响应信息
    private Object body=new Object(); // 账户信息业务对象

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

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public NettyMessageModel(Object obj) {
        this.body = obj;
    }
    public NettyMessageModel() {
    }
}