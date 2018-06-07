package com.vvme.vok.builder;

import com.vvme.vok.type.RequestType;
import com.vvme.vok.model.ParamsModel;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 16:44.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public abstract class GenericRequestBuilder<T> implements VokRequestBuilder {

    private ParamsModel.Builder mParamsBuilder;

    public GenericRequestBuilder() {
    }

    public GenericRequestBuilder(String url, RequestType type) {
        getParamsBuilder().url(url);
        if (type == null) {
            type = RequestType.GET;
        }
        getParamsBuilder().requestType(type);
    }

    public GenericRequestBuilder(ParamsModel.Builder paramsBuilder) {
        this.mParamsBuilder = paramsBuilder;
    }

    protected final ParamsModel.Builder getParamsBuilder() {
        if (mParamsBuilder == null) {
            mParamsBuilder = new ParamsModel.Builder();
        }
        return mParamsBuilder;
    }

    protected final ParamsModel getParamsModel() {
        return getParamsBuilder().build();
    }


}
