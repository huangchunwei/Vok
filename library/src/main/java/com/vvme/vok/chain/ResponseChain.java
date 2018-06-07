package com.vvme.vok.chain;

import com.vvme.vok.config.VokConfig;
import com.vvme.vok.model.ParamsModel;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 12:28.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public final class ResponseChain {

    private ResponseChain() {

    }

    public static ResponseChain get() {
        return new ResponseChain();
    }

    public void fakeDataChain(Response response, ParamsModel paramsModel) {
        chain(null, response, paramsModel);
    }

    public void chain(Call call, Response response, ParamsModel paramsModel) {
        if (paramsModel != null) {
            //Current request exclusive response interceptor
            if (paramsModel.getResponseChain() != null) {
                if (!paramsModel.getResponseChain().chain(call, response, paramsModel, call == null)) {
                    return;
                }
            }
            //Response interceptor for global all requests
            if (VokConfig.get().getResponseChain() != null) {
                if (!VokConfig.get().getResponseChain().chain(call, response, paramsModel, call == null)) {
                    return;
                }
            }
        }
        ResponseChainProxy.get(paramsModel).processResponse(call, response);
    }

}
