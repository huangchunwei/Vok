package com.vvme.vok.interfaces;

import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.response.VokResponse;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 16:54.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface ResponseExecutor<T> {


    void go(VokCallback<T> callback);

    VokResponse syncGo();


}
