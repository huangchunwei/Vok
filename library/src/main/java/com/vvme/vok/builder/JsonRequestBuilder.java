package com.vvme.vok.builder;

import com.vvme.vok.interfaces.IFakeDataDebug;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.type.ResponseType;
import com.vvme.vok.interfaces.UndefinedRequestExecutor;
import com.vvme.vok.dispatcher.VokRequestDispatcher;
import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.model.ParamsModel;
import com.vvme.vok.response.FakeResponseBuilder;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 21:49.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class JsonRequestBuilder extends TypeRequestBuilder<JsonRequestBuilder> implements UndefinedRequestExecutor, IFakeDataDebug<JsonRequestBuilder> {

    public JsonRequestBuilder() {
        getParamsBuilder().responseType(ResponseType.JSON);
    }

    public JsonRequestBuilder(String url, RequestType type) {
        super(url, type);
        getParamsBuilder().responseType(ResponseType.JSON);
    }

    public JsonRequestBuilder(ParamsModel.Builder paramsBuilder) {
        super(paramsBuilder);
        getParamsBuilder().responseType(ResponseType.JSON);
    }

    @Override
    public <C> void go(VokCallback<C> callback) {
        getParamsBuilder().callback(callback);
        VokRequestDispatcher.get().enqueue(getParamsModel());
    }

    @Override
    public Response syncGo() {
        return VokRequestDispatcher.get().syncExecute(getParamsModel());
    }

    @Override
    public JsonRequestBuilder openFakeData(boolean open) {
        getParamsBuilder().openFakeDataDebug(open);
        return this;
    }

    @Override
    public JsonRequestBuilder fakeDataBuilder(FakeResponseBuilder fakeResponseBuilder) {
        getParamsBuilder().fakeResponseBuilder(fakeResponseBuilder);
        return this;
    }
}
