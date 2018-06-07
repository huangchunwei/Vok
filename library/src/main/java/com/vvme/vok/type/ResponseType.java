package com.vvme.vok.type;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 12:31.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public enum ResponseType {
    STRING,
    BITMAP,
    JSON,
    FILE;

    public boolean isString(){
        return this == STRING;
    }

    public boolean isBitmap(){
        return this == BITMAP;
    }
    public boolean isJson(){
        return this == JSON;
    }
    public boolean isFile(){
        return this == FILE;
    }
}
