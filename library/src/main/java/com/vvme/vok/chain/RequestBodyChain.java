package com.vvme.vok.chain;

import android.support.annotation.NonNull;

import com.vvme.vok.callback.ProgressCallback;
import com.vvme.vok.model.ParamsModel;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 10:08.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface RequestBodyChain {

    RequestBody buildBody(@NonNull Request.Builder builder, @NonNull ParamsModel paramsModel);

    RequestBody wrapRequestBody(RequestBody requestBody, ProgressCallback callback);


}
