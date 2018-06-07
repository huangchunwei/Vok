package com.vvme.vok.chain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vvme.vok.utils.ResponseHelper;
import com.vvme.vok.config.VokConfig;
import com.vvme.vok.callback.ProgressCallback;
import com.vvme.vok.model.FileSource;
import com.vvme.vok.model.ParamsModel;
import com.vvme.vok.request.CountingRequestBody;
import com.vvme.vok.utils.Utils;

import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 10:33.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class RequestChainProxy implements RequestBodyChain {

    private static final String TAG = RequestChainProxy.class.getSimpleName();

    private ParamsModel mParamsModel;

    public static RequestChainProxy get() {
        return new RequestChainProxy();
    }

    public Request chain(@NonNull ParamsModel paramsModel) {
        mParamsModel = paramsModel;
        Log.d(TAG, "-------------RequestChain start-------------");
        //1.Building request parameters & request header parameters
        Request.Builder builder = new Request.Builder();
        //Merge request header
        Log.d(TAG, "-------------RequestChain add Headers: -------------");
        if (paramsModel.getCustomHeaderChain() != null) {
            Headers headers = paramsModel.getCustomHeaderChain().headers(paramsModel.getHeaders());
            if (headers != null) {
                builder.headers(headers);
            }
        } else if (VokConfig.get().getCustomHeaderChain() != null) {
            Headers headers = VokConfig.get().getCustomHeaderChain().headers(paramsModel.getHeaders());
            if (headers != null) {
                builder.headers(headers);
            }
        } else {
            builder = appendHeaders(builder, paramsModel.getHeaders());
        }
        if (paramsModel.getRequestType().isGet()) {
            Log.d(TAG, "-------------RequestChain is Get: -------------");
            String requestUrl;
            if (paramsModel.getMergeParamsChain() != null) {
                requestUrl = paramsModel.getMergeParamsChain().mergeParamsToUrl(paramsModel.getUrl(), paramsModel.getParams());
            } else if (VokConfig.get().getMergeParamsChain() != null) {
                requestUrl = VokConfig.get().getMergeParamsChain().mergeParamsToUrl(paramsModel.getUrl(), paramsModel.getParams());
            } else {
                requestUrl = Utils.mergeParams(paramsModel.getUrl(), paramsModel.getParams());
            }
            Log.d(TAG, "-------------RequestChain Get request url is: \n" + requestUrl);
            builder.url(requestUrl);
        } else if (paramsModel.getRequestType().isPost()) {
            Log.d(TAG, "-------------RequestChain is Post: -------------");
            Log.d(TAG, "-------------RequestChain Get request url is: \n" + paramsModel.getUrl());
            RequestBody requestBody;
            if (paramsModel.getRequestBodyBuildChain() != null) {
                requestBody = paramsModel.getRequestBodyBuildChain().wrapRequestBody(paramsModel.getRequestBodyBuildChain().buildBody(builder, paramsModel), paramsModel.getProgressCallback());
            } else if (VokConfig.get().getRequestBodyChain() != null) {
                requestBody = VokConfig.get().getRequestBodyChain().wrapRequestBody(VokConfig.get().getRequestBodyChain().buildBody(builder, paramsModel), paramsModel.getProgressCallback());
            } else {
                requestBody = wrapRequestBody(buildBody(builder, paramsModel), paramsModel.getProgressCallback());
            }
            builder.url(paramsModel.getUrl()).post(requestBody);
        } else {
            // TODO: 2018/6/7 Other forms of request to be treated
        }
        return builder.build();
    }

    private Request.Builder appendHeaders(Request.Builder builder, Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return builder;
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        Headers header = headerBuilder.build();
        builder.headers(header);
        Log.d(TAG, "-------------RequestChain Headers is: \n" + header.toString());
        return builder;
    }

    private void addParamsToRequestBody(MultipartBody.Builder builder, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
        }
    }

    private RequestBody addParamsToRequestBody(FormBody.Builder builder, Map<String, String> params, boolean isEncode) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                if (isEncode) {
                    builder.addEncoded(key, params.get(key));
                } else {
                    builder.add(key, params.get(key));
                }
            }
        }
        FormBody formBody = builder.build();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < formBody.size(); i++) {
            sb.append("key: ").append(formBody.name(i)).append(", value: ").append(formBody.value(i)).append("\n");
        }
        Log.d(TAG, "-------------RequestChain Post body is: \n" + sb.toString());
        return formBody;
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;
        try {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    @Override
    public RequestBody buildBody(@NonNull Request.Builder builder, @NonNull ParamsModel paramsModel) {
        RequestBody requestBody;
        //Here we need to deal with common post requests and requests for uploading files.
        if (paramsModel.getFileSources() != null && !paramsModel.getFileSources().isEmpty()) {
            //File upload or mixed request
            MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            //Custom parameter building interceptor
            if (paramsModel.getParamsBuildChain() != null) {
                paramsModel.getParamsBuildChain().addParamsToRequestBody(multipartBuilder, paramsModel.getParams(), paramsModel.getFileSources());
            } else if (VokConfig.get().getParamsBuildChain() != null) {
                VokConfig.get().getParamsBuildChain().addParamsToRequestBody(multipartBuilder, paramsModel.getParams(), paramsModel.getFileSources());
            } else {
                addParamsToRequestBody(multipartBuilder, paramsModel.getParams());
                for (FileSource fileSource : paramsModel.getFileSources()) {
                    RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileSource.getFilename())), fileSource.getFile());
                    multipartBuilder.addFormDataPart(fileSource.getKey(), fileSource.getFilename(), fileBody);
                }
            }
            requestBody = multipartBuilder.build();
        } else {
            //Common post request
            FormBody.Builder formBuilder = new FormBody.Builder();
            //Custom parameters build connectors
            if (paramsModel.getParamsBuildChain() != null) {
                paramsModel.getParamsBuildChain().addParamsToRequestBody(formBuilder, paramsModel.getParams());
                requestBody = formBuilder.build();
            } else if (VokConfig.get().getParamsBuildChain() != null) {
                VokConfig.get().getParamsBuildChain().addParamsToRequestBody(formBuilder, paramsModel.getParams());
                requestBody = formBuilder.build();
            } else {
                requestBody = addParamsToRequestBody(formBuilder, paramsModel.getParams(), false);
            }
        }
        return requestBody;
    }

    @Override
    public RequestBody wrapRequestBody(RequestBody requestBody, final ProgressCallback callback) {
        if (callback == null) {
            return requestBody;
        }
        if (mParamsModel.getFileSources() == null || mParamsModel.getFileSources().isEmpty()) {//No file, just an ordinary request
            return requestBody;
        }
        final long[] startTime = {System.currentTimeMillis()};
        final long duration = mParamsModel.getProgressPublishTime();
        return new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {
                if (duration <= 0) {
                    ResponseHelper.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.progress(mParamsModel.getId(), contentLength, bytesWritten, bytesWritten * 100.0f / contentLength);
                        }
                    });
                } else {
                    if (System.currentTimeMillis() - startTime[0] >= duration) {
                        startTime[0] = System.currentTimeMillis();
                        ResponseHelper.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.progress(mParamsModel.getId(), contentLength, bytesWritten, bytesWritten * 100.0f / contentLength);
                            }
                        });
                    }
                }
            }
        });
    }
}
