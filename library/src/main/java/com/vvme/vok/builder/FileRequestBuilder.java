package com.vvme.vok.builder;

import com.vvme.vok.interfaces.IFile;
import com.vvme.vok.interfaces.IProgressConfig;
import com.vvme.vok.interfaces.RequestExecutor;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.type.ResponseType;
import com.vvme.vok.dispatcher.VokRequestDispatcher;
import com.vvme.vok.callback.ProgressCallback;
import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.model.ParamsModel;

import java.io.File;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 21:47.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class FileRequestBuilder extends TypeRequestBuilder<FileRequestBuilder> implements IFile<FileRequestBuilder>, IProgressConfig<FileRequestBuilder>, RequestExecutor<File> {

    public FileRequestBuilder() {
        getParamsBuilder().responseType(ResponseType.FILE);
    }

    public FileRequestBuilder(String url, RequestType type) {
        super(url, type);
        getParamsBuilder().responseType(ResponseType.FILE);
    }

    public FileRequestBuilder(ParamsModel.Builder paramsBuilder) {
        super(paramsBuilder);
        getParamsBuilder().responseType(ResponseType.FILE);
    }

    @Override
    public void go(VokCallback<File> callback) {
        getParamsBuilder().callback(callback);
        VokRequestDispatcher.get().enqueue(getParamsModel());
    }

    @Override
    public Response syncGo() {
        return VokRequestDispatcher.get().syncExecute(getParamsModel());
    }

    @Override
    public FileRequestBuilder filePath(String filePath) {
        getParamsBuilder().filePath(filePath);
        return this;
    }

    @Override
    public FileRequestBuilder fileName(String fileName) {
        getParamsBuilder().fileName(fileName);
        return this;
    }

    @Override
    public FileRequestBuilder progressCallback(ProgressCallback callback) {
        getParamsBuilder().progressCallback(callback);
        return this;
    }

    @Override
    public FileRequestBuilder progressPublishTime(long publishTime) {
        getParamsBuilder().progressPublishTime(publishTime);
        return this;
    }
}
