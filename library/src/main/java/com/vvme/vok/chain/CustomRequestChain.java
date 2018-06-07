package com.vvme.vok.chain;

import android.support.annotation.NonNull;

import com.vvme.vok.model.ParamsModel;

import okhttp3.Request;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 10:36.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: 整个Request所有的构建过程自己处理,系统不处理
 */
public interface CustomRequestChain {

    Request chain(@NonNull ParamsModel paramsModel);

}
