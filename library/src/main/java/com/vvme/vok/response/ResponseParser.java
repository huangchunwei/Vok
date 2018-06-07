package com.vvme.vok.response;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 13:30.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public abstract class ResponseParser<T> {

    public abstract T parseResponse(int id, Response response, Type type) throws Exception;

}
