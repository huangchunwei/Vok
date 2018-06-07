package com.vvme.vok.interfaces;

import com.vvme.vok.callback.VokCallback;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 21:20.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface RequestExecutor<T> {

    void go(VokCallback<T> callback);

    Response syncGo();

}
