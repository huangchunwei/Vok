package com.vvme.vok.builder;

import android.graphics.Bitmap;

import com.vvme.vok.interfaces.IProgress;
import com.vvme.vok.interfaces.IProgressConfig;
import com.vvme.vok.interfaces.RequestExecutor;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.type.ResponseType;
import com.vvme.vok.dispatcher.VokRequestDispatcher;
import com.vvme.vok.callback.ProgressCallback;
import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.model.ParamsModel;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 21:45.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class BitmapRequestBuilder extends TypeRequestBuilder<BitmapRequestBuilder> implements RequestExecutor<Bitmap>, IProgress<BitmapRequestBuilder>, IProgressConfig<BitmapRequestBuilder> {

    public BitmapRequestBuilder() {
        getParamsBuilder().responseType(ResponseType.BITMAP);
    }

    public BitmapRequestBuilder(String url, RequestType type) {
        super(url, type);
        getParamsBuilder().responseType(ResponseType.BITMAP);
    }

    public BitmapRequestBuilder(ParamsModel.Builder paramsBuilder) {
        super(paramsBuilder);
        getParamsBuilder().responseType(ResponseType.BITMAP);
    }

    @Override
    public void go(VokCallback<Bitmap> callback) {
        getParamsBuilder().callback(callback);
        VokRequestDispatcher.get().enqueue(getParamsModel());
    }

    @Override
    public Response syncGo() {
        return VokRequestDispatcher.get().syncExecute(getParamsModel());
    }

    @Override
    public BitmapRequestBuilder progressCallback(ProgressCallback callback) {
        getParamsBuilder().progressCallback(callback);
        return this;
    }

    @Override
    public BitmapRequestBuilder progressPublishTime(long publishTime) {
        getParamsBuilder().progressPublishTime(publishTime);
        return this;
    }
}
