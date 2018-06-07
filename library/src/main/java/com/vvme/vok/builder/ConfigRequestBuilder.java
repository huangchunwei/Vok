package com.vvme.vok.builder;

import com.vvme.vok.chain.CustomResponseChain;
import com.vvme.vok.parser.CustomResponseParser;
import com.vvme.vok.interfaces.IConfig;
import com.vvme.vok.interfaces.IRequestChain;
import com.vvme.vok.interfaces.IResponseChain;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.chain.CustomHeaderChain;
import com.vvme.vok.chain.CustomRequestChain;
import com.vvme.vok.model.ParamsModel;
import com.vvme.vok.response.ResponseProcessor;

import okhttp3.OkHttpClient;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/5 19:07.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class ConfigRequestBuilder<T> extends GenericRequestBuilder<T> implements IConfig<T>, IResponseChain<T>, IRequestChain<T>, VokRequestBuilder {

    public ConfigRequestBuilder() {
    }

    public ConfigRequestBuilder(String url, RequestType type) {
        super(url, type);
    }

    public ConfigRequestBuilder(ParamsModel.Builder builder) {
        super(builder);
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
    public T responseChain(CustomResponseChain responseChain) {
        getParamsBuilder().responseChain(responseChain);
        return (T) this;
    }

    @Override
    public T responseProcessor(ResponseProcessor responseProcessor) {
        getParamsBuilder().responseProcessor(responseProcessor);
        return (T) this;
    }

    @Override
    public <C> T responseParser(CustomResponseParser<C> responseParser) {
        getParamsBuilder().responseParser(responseParser);
        return (T) this;
    }

    @Override
    public T requestChain(CustomRequestChain requestChain) {
        getParamsBuilder().customRequestChain(requestChain);
        return (T) this;
    }

    @Override
    public T requestHeaderChain(CustomHeaderChain headerChain) {
        getParamsBuilder().headerChain(headerChain);
        return (T) this;
    }
}
