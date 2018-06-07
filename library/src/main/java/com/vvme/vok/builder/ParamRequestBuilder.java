package com.vvme.vok.builder;

import com.vvme.vok.interfaces.IConfig;
import com.vvme.vok.interfaces.IHeaders;
import com.vvme.vok.interfaces.IParams;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.interfaces.ResultType;
import com.vvme.vok.model.ParamsModel;

import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 16:49.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class ParamRequestBuilder<T> extends GenericRequestBuilder<T> implements IParams<T>, IHeaders<T>, IConfig<T>, ResultType {

    public ParamRequestBuilder(String url, RequestType type) {
        getParamsBuilder().url(url);
        getParamsBuilder().requestType(type);
    }

    public ParamRequestBuilder(ParamsModel.Builder paramsBuilder) {
        super(paramsBuilder);
    }

    @Override
    public T param(String key, String value) {
        getParamsBuilder().params(key, value);
        return (T) this;
    }

    @Override
    public T params(Map<String, String> params) {
        getParamsBuilder().params(params);
        return (T) this;
    }

    @Override
    public T header(String key, String value) {
        getParamsBuilder().headers(key, value);
        return (T) this;
    }

    @Override
    public T headers(Map<String, String> headers) {
        getParamsBuilder().headers(headers);
        return (T) this;
    }

    @Override
    public T client(OkHttpClient client) {
        getParamsBuilder().client(client);
        return (T) this;
    }

    @Override
    public T connectTimeout(long time) {
        getParamsBuilder().connectTimeout(time);
        return (T) this;
    }

    @Override
    public T readTimeout(long time) {
        getParamsBuilder().readTimeout(time);
        return (T) this;
    }

    @Override
    public T writeTimeout(long time) {
        getParamsBuilder().writeTimeout(time);
        return (T) this;
    }

    @Override
    public StringRequestBuilder asString() {
        return new StringRequestBuilder(getParamsBuilder());
    }

    @Override
    public BitmapRequestBuilder asBitmap() {
        return new BitmapRequestBuilder(getParamsBuilder());
    }

    @Override
    public JsonRequestBuilder asJson() {
        return new JsonRequestBuilder(getParamsBuilder());
    }

    @Override
    public FileRequestBuilder asFile() {
        return new FileRequestBuilder(getParamsBuilder());
    }
}
