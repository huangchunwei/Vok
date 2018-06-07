package com.vvme.vok.builder;

import com.vvme.vok.interfaces.IFakeDataDebug;
import com.vvme.vok.interfaces.IHeaders;
import com.vvme.vok.interfaces.IParams;
import com.vvme.vok.interfaces.RequestExecutor;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.type.ResponseType;
import com.vvme.vok.dispatcher.VokRequestDispatcher;
import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.model.ParamsModel;
import com.vvme.vok.response.FakeResponseBuilder;

import java.util.Map;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 21:27.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class StringRequestBuilder extends ConfigRequestBuilder<StringRequestBuilder> implements IParams<StringRequestBuilder>, IHeaders<StringRequestBuilder>, IFakeDataDebug<StringRequestBuilder>, RequestExecutor<String> {

    public StringRequestBuilder() {
    }

    public StringRequestBuilder(String url, RequestType type) {
        super(url, type);
    }

    public StringRequestBuilder(ParamsModel.Builder paramsBuilder) {
        super(paramsBuilder);
        getParamsBuilder().responseType(ResponseType.STRING);
    }

    @Override
    public void go(VokCallback<String> callback) {
        getParamsBuilder().callback(callback);
        VokRequestDispatcher.get().enqueue(getParamsModel());
    }

    @Override
    public Response syncGo() {
        return VokRequestDispatcher.get().syncExecute(getParamsModel());
    }

    @Override
    public StringRequestBuilder header(String key, String value) {
        getParamsBuilder().headers(key, value);
        return this;
    }

    @Override
    public StringRequestBuilder headers(Map<String, String> headers) {
        getParamsBuilder().headers(headers);
        return this;
    }

    @Override
    public StringRequestBuilder param(String key, String value) {
        getParamsBuilder().params(key, value);
        return this;
    }

    @Override
    public StringRequestBuilder params(Map<String, String> params) {
        getParamsBuilder().params(params);
        return this;
    }


    @Override
    public StringRequestBuilder openFakeData(boolean open) {
        getParamsBuilder().openFakeDataDebug(open);
        return this;
    }

    @Override
    public StringRequestBuilder fakeDataBuilder(FakeResponseBuilder fakeResponseBuilder) {
        getParamsBuilder().fakeResponseBuilder(fakeResponseBuilder);
        return this;
    }
}
