package com.vvme.vok.chain;

import android.support.annotation.NonNull;

import com.vvme.vok.config.VokConfig;
import com.vvme.vok.model.ParamsModel;

import okhttp3.Request;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 10:41.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public final class RequestChain {

    private static final String TAG = RequestChain.class.getSimpleName();

    private RequestChain() {

    }

    public static RequestChain get() {
        return new RequestChain();
    }

    public Request chain(@NonNull ParamsModel paramsModel) {
        if (paramsModel.getCustomRequestChain() != null) {
            return paramsModel.getCustomRequestChain().chain(paramsModel);
        }
        if (VokConfig.get().getCustomRequestChain() != null) {
            return VokConfig.get().getCustomRequestChain().chain(paramsModel);
        }
        return RequestChainProxy.get().chain(paramsModel);
    }
}
