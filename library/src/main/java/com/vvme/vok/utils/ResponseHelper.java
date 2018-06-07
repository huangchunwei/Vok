package com.vvme.vok.utils;

import com.vvme.vok.Vok;
import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.chain.ResponseChainProxy;
import com.vvme.vok.model.ParamsModel;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 12:54.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class ResponseHelper {


    public static void sendStart(final VokCallback callback) {
        Vok.platform().execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onStart();
                }
            }
        });
    }

    public static void sendError(final Throwable throwable, final VokCallback callback) {
        Vok.platform().execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(throwable);
                }
            }
        });
    }

    public static void sendFailed(final int errorCode, final String errorMsg, final VokCallback callback) {
        Vok.platform().execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailed(errorCode, errorMsg);
                }
            }
        });
    }

    public static <T> void sendSuccess(final T result, final VokCallback<T> callback) {
        Vok.platform().execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        });
    }

    public static void post(Runnable runnable) {
        Vok.platform().execute(runnable);
    }

    public static Response newResponse(Request request, String responseStr) {
        Response.Builder builder = new Response.Builder();
        if (responseStr != null) {
            byte[] bytes = responseStr.getBytes(Charset.defaultCharset());
            InputStream stringStream = getStringStream(bytes);
            if (stringStream != null) {
                Source source = Okio.source(stringStream);
                BufferedSource bufferedSource = Okio.buffer(source);
                return builder.code(200)
                        .request(request)
                        .message("OK")
                        .protocol(Protocol.HTTP_1_1)
                        .sentRequestAtMillis(-1L)
                        .receivedResponseAtMillis(System.currentTimeMillis())
                        .body(new RealResponseBody("application/json;charset=utf-8", bytes.length, bufferedSource))
                        .build();
            }
        }
        return null;
    }

    public static InputStream getStringStream(String sInputString, Charset charset) {
        try {
            if (sInputString == null) {
                return null;
            }
            return new ByteArrayInputStream(sInputString.getBytes(charset));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getStringStream(byte[] bytes) {
        try {
            return new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object outCallParseResponse(ParamsModel paramsModel, Response response, Type type) {
        try {
            return ResponseChainProxy.get(paramsModel).outCallParserResponse(response, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
