package com.vvme.vok.model;

import com.vvme.vok.chain.CustomResponseChain;
import com.vvme.vok.parser.CustomResponseParser;
import com.vvme.vok.type.RequestType;
import com.vvme.vok.type.ResponseType;
import com.vvme.vok.callback.ProgressCallback;
import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.chain.CustomHeaderChain;
import com.vvme.vok.chain.CustomRequestChain;
import com.vvme.vok.chain.MergeParamsChain;
import com.vvme.vok.chain.ParamsBuildChain;
import com.vvme.vok.chain.RequestBodyChain;
import com.vvme.vok.response.FakeResponseBuilder;
import com.vvme.vok.response.ResponseProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 17:05.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class ParamsModel implements Cloneable {

    private Builder mBuilder;

    public ParamsModel(Builder builder) {
        if (builder == null) {
            builder = new Builder();
        }
        this.mBuilder = builder;
    }

    public String getUrl() {
        return mBuilder.url;
    }

    public RequestType getRequestType() {
        return mBuilder.requestType;
    }

    public Map<String, String> getParams() {
        return mBuilder.params;
    }

    public Map<String, String> getHeaders() {
        return mBuilder.headers;
    }

    public OkHttpClient getClient() {
        return mBuilder.client;
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

    public int getId() {
        return mBuilder.id;
    }

    public VokCallback getCallback() {
        return mBuilder.callback;
    }

    public ResponseType getResponseType() {
        return mBuilder.responseType;
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

    public ProgressCallback getProgressCallback() {
        return mBuilder.progressCallback;
    }

    public String getFilePath() {
        return mBuilder.filePath;
    }

    public String getFileName() {
        return mBuilder.fileName;
    }

    public File getFile() {
        return new File(mBuilder.filePath, mBuilder.fileName);
    }

    public boolean getOpenFakeDataDebug() {
        return mBuilder.openFakeDataDebug;
    }

    public FakeResponseBuilder getFakeResponseBuilder() {
        return mBuilder.fakeResponseBuilder;
    }

    public CustomRequestChain getCustomRequestChain() {
        return mBuilder.customRequestChain;
    }

    public RequestBodyChain getRequestBodyBuildChain() {
        return mBuilder.bodyBuildChain;
    }

    public MergeParamsChain getMergeParamsChain() {
        return mBuilder.mergeParamsChain;
    }

    public CustomHeaderChain getCustomHeaderChain() {
        return mBuilder.headerChain;
    }

    public List<FileSource> getFileSources() {
        return mBuilder.fileSources;
    }

    public ParamsBuildChain getParamsBuildChain() {
        return mBuilder.paramsBuildChain;
    }

    public boolean getOpenBreakpointDownload() {
        return mBuilder.openBreakpointDownload;
    }

    public boolean getOpenMultiThreadDownload() {
        return mBuilder.openMultiThreadDownload;
    }

    public int getMultiThreadCount() {
        return mBuilder.multiThreadCount;
    }

    public long getProgressPublishTime() {
        return mBuilder.progressPublishTime;
    }

    public boolean getDeleteFileExist() {
        return mBuilder.deleteFileExist;
    }

    @Override
    protected ParamsModel clone() throws CloneNotSupportedException {
        return (ParamsModel) super.clone();
    }

    public static final class Builder {
        //        WeakReference<Context> context;
        String url;
        ResponseType responseType;
        RequestType requestType;
        Map<String, String> params;
        Map<String, String> headers;
        OkHttpClient client;
        long connectTimeout;
        long readTimeout;
        long writeTimeout;
        int id;
        VokCallback callback;
        CustomResponseChain responseChain;
        ResponseProcessor responseProcessor;
        CustomResponseParser responseParser;
        ProgressCallback progressCallback;
        String filePath;
        String fileName;
        boolean openFakeDataDebug;
        FakeResponseBuilder fakeResponseBuilder;
        CustomRequestChain customRequestChain;
        RequestBodyChain bodyBuildChain;
        MergeParamsChain mergeParamsChain;
        CustomHeaderChain headerChain;
        List<FileSource> fileSources;
        ParamsBuildChain paramsBuildChain;
        boolean deleteFileExist = true;
        long progressPublishTime;
        boolean openBreakpointDownload = true;
        boolean openMultiThreadDownload = false;
        int multiThreadCount = 3;

        public void url(String url) {
            this.url = url;
        }

        public void requestType(RequestType requestType) {
            this.requestType = requestType;
        }

        public void params(String key, String value) {
            if (this.params == null) {
                this.params = new LinkedHashMap<>();
            }
            this.params.put(key, value);
        }

        public void params(Map<String, String> params) {
            this.params = params;
        }

        public void headers(String key, String value) {
            if (this.headers == null) {
                this.headers = new LinkedHashMap<>();
            }
            this.headers.put(key, value);
        }

        public void headers(Map<String, String> headers) {
            this.headers = headers;
        }

        public void client(OkHttpClient client) {
            this.client = client;
        }

        public void connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public void readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
        }

        public void writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
        }

        public void id(int id) {
            this.id = id;
        }

        public <T> void callback(VokCallback<T> callback) {
            this.callback = callback;
        }

        public void responseType(ResponseType responseType) {
            this.responseType = responseType;
        }

        public void responseChain(CustomResponseChain responseChain) {
            this.responseChain = responseChain;
        }

        public void responseProcessor(ResponseProcessor responseProcessor) {
            this.responseProcessor = responseProcessor;
        }

        public <T> void responseParser(CustomResponseParser<T> responseParser) {
            this.responseParser = responseParser;
        }

        public void progressCallback(ProgressCallback callback) {
            this.progressCallback = callback;
        }

        public void filePath(String filePath) {
            this.filePath = filePath;
        }

        public void fileName(String fileName) {
            this.fileName = fileName;
        }

        public void openFakeDataDebug(boolean openFakeDataDebug) {
            this.openFakeDataDebug = openFakeDataDebug;
        }

        public void fakeResponseBuilder(FakeResponseBuilder fakeResponseBuilder) {
            this.fakeResponseBuilder = fakeResponseBuilder;
        }

        public void customRequestChain(CustomRequestChain customRequestChain) {
            this.customRequestChain = customRequestChain;
        }

        public void requestBodyBuildChain(RequestBodyChain requestBodyBuildChain) {
            this.bodyBuildChain = requestBodyBuildChain;
        }

        public void mergeParamsChain(MergeParamsChain mergeParamsChain) {
            this.mergeParamsChain = mergeParamsChain;
        }

        public void headerChain(CustomHeaderChain customHeaderChain) {
            this.headerChain = customHeaderChain;
        }

        public void fileSources(List<FileSource> fileSources) {
            this.fileSources = fileSources;
        }

        public void fileSources(FileSource fileSource) {
            if (this.fileSources == null) {
                this.fileSources = new ArrayList<>();
            }
            this.fileSources.add(fileSource);
        }

        public void paramsBuildChain(ParamsBuildChain paramsBuildChain) {
            this.paramsBuildChain = paramsBuildChain;
        }

        public void deleteWhenFileExist(boolean delete) {
            this.deleteFileExist = delete;
        }

        public void progressPublishTime(long time) {
            this.progressPublishTime = time;
        }

        public void breakpointDownload(boolean openBreakpointDownload) {
            this.openBreakpointDownload = openBreakpointDownload;
        }

        public void multiThreadDownload(int multipleThreadCount) {
            this.openMultiThreadDownload = true;
            this.multiThreadCount = multipleThreadCount;
        }

        public ParamsModel build() {
            return new ParamsModel(this);
        }
    }
}
