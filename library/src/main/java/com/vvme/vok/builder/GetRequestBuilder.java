package com.vvme.vok.builder;

import com.vvme.vok.interfaces.IMergeParams;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.chain.MergeParamsChain;
import com.vvme.vok.model.ParamsModel;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 13:03.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class GetRequestBuilder extends ParamRequestBuilder<GetRequestBuilder> implements IMergeParams<GetRequestBuilder> {

    public GetRequestBuilder(String url) {
        this(url, RequestType.GET);
    }

    public GetRequestBuilder(String url, RequestType type) {
        super(url, type);
    }

    public GetRequestBuilder(ParamsModel.Builder builder) {
        super(builder);
        getParamsBuilder().requestType(RequestType.GET);
    }

    @Override
    public GetRequestBuilder mergeParamsChain(MergeParamsChain mergeParamsChain) {
        getParamsBuilder().mergeParamsChain(mergeParamsChain);
        return this;
    }
}
