package com.vvme.vok.builder;

import com.vvme.vok.interfaces.IFileSource;
import com.vvme.vok.interfaces.IParamsBuilder;
import com.vvme.vok.interfaces.IRequestBodyChain;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.chain.ParamsBuildChain;
import com.vvme.vok.chain.RequestBodyChain;
import com.vvme.vok.model.FileSource;
import com.vvme.vok.model.ParamsModel;

import java.util.List;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 13:03.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class PostRequestBuilder extends ParamRequestBuilder<PostRequestBuilder> implements IParamsBuilder<PostRequestBuilder>, IFileSource<PostRequestBuilder>, IRequestBodyChain<PostRequestBuilder> {

    public PostRequestBuilder(String url) {
        this(url, RequestType.POST);
    }

    public PostRequestBuilder(String url, RequestType type) {
        super(url, type);
    }

    public PostRequestBuilder(ParamsModel.Builder paramsBuilder) {
        super(paramsBuilder);
        getParamsBuilder().requestType(RequestType.POST);
    }

    @Override
    public PostRequestBuilder paramsBuildChain(ParamsBuildChain paramsBuildChain) {
        getParamsBuilder().paramsBuildChain(paramsBuildChain);
        return this;
    }

    @Override
    public PostRequestBuilder requestBodyChain(RequestBodyChain requestBodyChain) {
        getParamsBuilder().requestBodyBuildChain(requestBodyChain);
        return this;
    }

    @Override
    public PostRequestBuilder fileSource(List<FileSource> fileSources) {
        getParamsBuilder().fileSources(fileSources);
        return this;
    }

    @Override
    public PostRequestBuilder fileSource(FileSource fileSource) {
        getParamsBuilder().fileSources(fileSource);
        return this;
    }
}
