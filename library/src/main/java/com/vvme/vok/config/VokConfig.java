package com.vvme.vok.config;

import com.vvme.vok.BuildConfig;
import com.vvme.vok.chain.CustomHeaderChain;
import com.vvme.vok.chain.CustomRequestChain;
import com.vvme.vok.chain.CustomResponseChain;
import com.vvme.vok.chain.MergeParamsChain;
import com.vvme.vok.chain.ParamsBuildChain;
import com.vvme.vok.chain.RequestBodyChain;
import com.vvme.vok.client.ClientFactory;
import com.vvme.vok.parser.CustomResponseParser;
import com.vvme.vok.response.ResponseProcessor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/5 17:56.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class VokConfig {

    private static final long DEFAULT_CONNECT_TIMEOUT = 15 * 1000;//单位:MS(毫秒)
    private static final long DEFAULT_READ_TIMEOUT = 15 * 1000;//单位:MS(毫秒)
    private static final long DEFAULT_WRITE_TIMEOUT = 15 * 1000;//单位:MS(毫秒)

    private static volatile VokConfig sVokConfig;
    private Builder mBuilder;
    private ClientFactory<OkHttpClient> CLIENT_FACTORY = new ClientFactory<OkHttpClient>() {
        @Override
        public OkHttpClient newClient() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(mBuilder.connectTimeout > 0 ? mBuilder.connectTimeout : DEFAULT_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
            builder.readTimeout(mBuilder.readTimeout > 0 ? mBuilder.readTimeout : DEFAULT_READ_TIMEOUT, TimeUnit.MILLISECONDS);
            builder.writeTimeout(mBuilder.writeTimeout > 0 ? mBuilder.writeTimeout : DEFAULT_WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
            builder.retryOnConnectionFailure(mBuilder.retryOnConnectionFailure);
            return builder.build();
        }
    };

    private VokConfig(Builder builder) {
        this.mBuilder = builder;
        if (mBuilder.clientFactory != null) {
            CLIENT_FACTORY = mBuilder.clientFactory;
        }
    }

    public static void init() {
        init(null);
    }

    public static void init(Builder builder) {
        if (sVokConfig == null) {
            synchronized (VokConfig.class) {
                if (sVokConfig == null) {
                    if (builder == null) {
                        builder = new Builder();
                    }
                    sVokConfig = new VokConfig(builder);
                }
            }
        }
    }

    public static VokConfig get() {
        if (sVokConfig == null) {
            synchronized (VokConfig.class) {
                if (sVokConfig == null) {
                    sVokConfig = new VokConfig(new Builder());
                }
            }
        }
        return sVokConfig;
    }

    public OkHttpClient getClient() {
        return CLIENT_FACTORY.newClient();
    }

    public boolean isDebug() {
        return mBuilder.isDebug;
    }

    public boolean isOpenFakeDataDebug() {
        return mBuilder.openFakeDataDebug;
    }

    public long getConnectTimeout() {
        return mBuilder.connectTimeout;
    }

    public long getReadTimeout() {
        return mBuilder.readTimeout;
    }

    public long getWriteTimeout() {
        return mBuilder.writeTimeout;
    }

    public CustomResponseChain getResponseChain() {
        return mBuilder.responseChain;
    }

    public ResponseProcessor getResponseProcessor() {
        return mBuilder.responseProcessor;
    }

    public CustomResponseParser getResponseParser() {
        return mBuilder.responseParser;
    }

    public CustomRequestChain getCustomRequestChain() {
        return mBuilder.customRequestChain;
    }

    public CustomHeaderChain getCustomHeaderChain() {
        return mBuilder.customHeaderChain;
    }

    public MergeParamsChain getMergeParamsChain() {
        return mBuilder.mergeParamsChain;
    }

    public ParamsBuildChain getParamsBuildChain() {
        return mBuilder.paramsBuildChain;
    }

    public RequestBodyChain getRequestBodyChain() {
        return mBuilder.requestBodyChain;
    }

    public static class Builder {
        boolean isDebug = BuildConfig.DEBUG;
        boolean openFakeDataDebug = false;
        ClientFactory<OkHttpClient> clientFactory;
        long connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        long readTimeout = DEFAULT_READ_TIMEOUT;
        long writeTimeout = DEFAULT_WRITE_TIMEOUT;
        ResponseProcessor responseProcessor;
        CustomResponseParser responseParser;
        CustomResponseChain responseChain;
        boolean retryOnConnectionFailure;
        //        Context context;
        CustomRequestChain customRequestChain;
        CustomHeaderChain customHeaderChain;
        MergeParamsChain mergeParamsChain;
        ParamsBuildChain paramsBuildChain;
        RequestBodyChain requestBodyChain;

        public Builder debug(boolean debug) {
            isDebug = debug;
            return this;
        }

        public Builder openFakeDataDebug(boolean openFakeDataDebug) {
            this.openFakeDataDebug = openFakeDataDebug;
            return this;
        }

        public Builder clientFactory(ClientFactory<OkHttpClient> clientFactory) {
            this.clientFactory = clientFactory;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setWriteTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder responseProcessor(ResponseProcessor responseProcessor) {
            this.responseProcessor = responseProcessor;
            return this;
        }

        public Builder responseParser(CustomResponseParser responseParser) {
            this.responseParser = responseParser;
            return this;
        }

        public Builder responseChain(CustomResponseChain responseChain) {
            this.responseChain = responseChain;
            return this;
        }

        public Builder retryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public Builder requestChain(CustomRequestChain customRequestChain) {
            this.customRequestChain = customRequestChain;
            return this;
        }

        public Builder requestBodyChain(RequestBodyChain requestBodyChain) {
            this.requestBodyChain = requestBodyChain;
            return this;
        }

        public Builder requestHeaderChain(CustomHeaderChain customHeaderChain) {
            this.customHeaderChain = customHeaderChain;
            return this;
        }

        public Builder paramsBuildChain(ParamsBuildChain paramsBuildChain) {
            this.paramsBuildChain = paramsBuildChain;
            return this;
        }

        public Builder mergeParamsChain(MergeParamsChain mergeParamsChain) {
            this.mergeParamsChain = mergeParamsChain;
            return this;
        }
    }

}
