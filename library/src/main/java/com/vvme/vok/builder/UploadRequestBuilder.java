package com.vvme.vok.builder;

import android.graphics.Bitmap;

import com.vvme.vok.interfaces.IFileSource;
import com.vvme.vok.interfaces.IHeaders;
import com.vvme.vok.interfaces.IParams;
import com.vvme.vok.interfaces.IParamsBuilder;
import com.vvme.vok.interfaces.IProgress;
import com.vvme.vok.interfaces.IProgressConfig;
import com.vvme.vok.interfaces.IRequestBodyChain;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.type.ResponseType;
import com.vvme.vok.interfaces.UndefinedRequestExecutor;
import com.vvme.vok.dispatcher.VokRequestDispatcher;
import com.vvme.vok.callback.ProgressCallback;
import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.chain.ParamsBuildChain;
import com.vvme.vok.chain.RequestBodyChain;
import com.vvme.vok.model.FileSource;
import com.vvme.vok.model.ParamsModel;
import com.vvme.vok.utils.Utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 13:12.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class UploadRequestBuilder extends TypeRequestBuilder<UploadRequestBuilder> implements IParams<UploadRequestBuilder>, IHeaders<UploadRequestBuilder>, IProgress<UploadRequestBuilder>, IProgressConfig<UploadRequestBuilder>, IParamsBuilder<UploadRequestBuilder>, IFileSource<UploadRequestBuilder>, IRequestBodyChain<UploadRequestBuilder>, UndefinedRequestExecutor {

    public UploadRequestBuilder(String url) {
        this(url, RequestType.POST);
    }

    public UploadRequestBuilder(String url, RequestType type) {
        super(url, type);
    }

    public UploadRequestBuilder(ParamsModel.Builder paramsBuilder) {
        super(paramsBuilder);
        getParamsBuilder().requestType(RequestType.POST);
    }

    @Override
    public <C> void go(VokCallback<C> callback) {
        try {
            Class typeClass = Utils.getGenericTypeClass(callback, 0);
            if (typeClass == String.class) {
                getParamsBuilder().responseType(ResponseType.STRING);
            } else if (typeClass == Bitmap.class) {
                getParamsBuilder().responseType(ResponseType.BITMAP);
            } else if (typeClass == File.class) {
                getParamsBuilder().responseType(ResponseType.FILE);
            } else if (typeClass == Object.class) {
                getParamsBuilder().responseType(ResponseType.STRING);
            } else {
                getParamsBuilder().responseType(ResponseType.JSON);
            }
        } catch (Exception e) {
            e.printStackTrace();
            getParamsBuilder().responseType(ResponseType.STRING);
        }
        getParamsBuilder().callback(callback);
        VokRequestDispatcher.get().enqueue(getParamsModel());
    }

    @Override
    public Response syncGo() {
        return VokRequestDispatcher.get().syncExecute(getParamsModel());
    }

    @Override
    public UploadRequestBuilder header(String key, String value) {
        getParamsBuilder().headers(key, value);
        return this;
    }

    @Override
    public UploadRequestBuilder headers(Map<String, String> headers) {
        getParamsBuilder().headers(headers);
        return this;
    }

    @Override
    public UploadRequestBuilder param(String key, String value) {
        getParamsBuilder().params(key, value);
        return this;
    }

    @Override
    public UploadRequestBuilder params(Map<String, String> params) {
        getParamsBuilder().params(params);
        return this;
    }

    @Override
    public UploadRequestBuilder paramsBuildChain(ParamsBuildChain paramsBuildChain) {
        getParamsBuilder().paramsBuildChain(paramsBuildChain);
        return this;
    }

    @Override
    public UploadRequestBuilder requestBodyChain(RequestBodyChain requestBodyChain) {
        getParamsBuilder().requestBodyBuildChain(requestBodyChain);
        return this;
    }

    @Override
    public UploadRequestBuilder fileSource(List<FileSource> fileSources) {
        getParamsBuilder().fileSources(fileSources);
        return this;
    }

    @Override
    public UploadRequestBuilder fileSource(FileSource fileSource) {
        getParamsBuilder().fileSources(fileSource);
        return this;
    }

    @Override
    public UploadRequestBuilder progressCallback(ProgressCallback callback) {
        getParamsBuilder().progressCallback(callback);
        return this;
    }

    @Override
    public UploadRequestBuilder progressPublishTime(long publishTime) {
        getParamsBuilder().progressPublishTime(publishTime);
        return this;
    }
}
