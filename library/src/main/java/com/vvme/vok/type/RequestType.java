package com.vvme.vok.type;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 21:51.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public enum RequestType {

    GET,
    POST;

    public boolean isGet() {
        return this == GET;
    }

    public boolean isPost() {
        return this == POST;
    }

}
