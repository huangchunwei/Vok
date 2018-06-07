package com.vvme.vok.response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 16:26.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class VokResponse {

    private int responseCode;
    private int myCode;
    private boolean isDirect = true;
    private String msg;
    private String body = "";

    public VokResponse() {
    }

    public int getCode() {
        return responseCode;
    }

    public void setCode(int code) {
        this.responseCode = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        if (msg == null) {
            msg = "";
        }
        this.msg = msg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        if (body == null) {
            body = "";
        }
        this.body = body;
    }

    public boolean isDirect() {
        return isDirect;
    }

    public void setDirect(boolean direct) {
        isDirect = direct;
    }

    public int getMyCode() {
        return myCode;
    }

    public void setMyCode(int myCode) {
        this.myCode = myCode;
    }
}
