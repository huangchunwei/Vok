package com.vvme.vok.interfaces;

import com.vvme.vok.model.ParamsModel;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/5 17:54.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IDispatcher {

    void enqueue(ParamsModel paramsModel);

    Response syncExecute(ParamsModel paramsModel);

}
