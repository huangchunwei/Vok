package com.vvme.vok.builder;

import com.vvme.vok.interfaces.IReqeustMethod;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.model.ParamsModel;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 13:11.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class DownloadRequestBuilder extends FileRequestBuilder implements IReqeustMethod<DownloadRequestBuilder, DownloadRequestBuilder> {


    public DownloadRequestBuilder() {
    }

    public DownloadRequestBuilder(String url) {
        this(url, RequestType.GET);
    }

    public DownloadRequestBuilder(String url, RequestType type) {
        super(url, type);
    }

    public DownloadRequestBuilder(ParamsModel.Builder paramsBuilder) {
        super(paramsBuilder);
    }

    @Override
    public DownloadRequestBuilder get() {
        getParamsBuilder().requestType(RequestType.GET);
        return this;
    }

    @Override
    public DownloadRequestBuilder post() {
        getParamsBuilder().requestType(RequestType.POST);
        return this;
    }
}
