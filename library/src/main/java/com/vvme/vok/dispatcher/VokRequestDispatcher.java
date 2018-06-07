package com.vvme.vok.dispatcher;

import android.util.Log;

import com.vvme.vok.utils.ResponseHelper;
import com.vvme.vok.config.VokConfig;
import com.vvme.vok.chain.RequestChain;
import com.vvme.vok.chain.ResponseChain;
import com.vvme.vok.interfaces.IDispatcher;
import com.vvme.vok.model.ParamsModel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/5 17:43.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class VokRequestDispatcher implements IDispatcher {

    private static volatile VokRequestDispatcher sInstance;

    private VokRequestDispatcher() {

    }

    public static VokRequestDispatcher get() {
        if (sInstance == null) {
            synchronized (VokRequestDispatcher.class) {
                if (sInstance == null) {
                    sInstance = new VokRequestDispatcher();
                }
            }
        }
        return sInstance;
    }


    @Override
    public void enqueue(final ParamsModel paramsModel) {
        Call call = buildCall(paramsModel);
        if (call != null) {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("Vok", "request failed: " + e.getMessage());
                    ResponseHelper.sendError(e, paramsModel.getCallback());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("Vok", "request success");
                    ResponseChain.get().chain(call, response, paramsModel);

                }
            });
        }
    }

    private Call buildCall(final ParamsModel paramsModel) {
        if (paramsModel == null) {
            throw new UnsupportedOperationException("ParamsModel can not be null");
        }
        ResponseHelper.sendStart(paramsModel.getCallback());
        //处理请求构建过程
        final Request request = RequestChain.get().chain(paramsModel);
        //需要假数据
        if (VokConfig.get().isOpenFakeDataDebug() && paramsModel.getOpenFakeDataDebug()) {
            if (paramsModel.getFakeResponseBuilder() != null) {
                String responseStr = paramsModel.getFakeResponseBuilder().buildFakeResponse();
                Response response = ResponseHelper.newResponse(request, responseStr);
                if (response != null) {
                    ResponseChain.get().fakeDataChain(response, paramsModel);
                }
                return null;
            }
        }
        //处理自定义Client
        OkHttpClient okHttpClient = VokConfig.get().getClient();
        if (paramsModel.getConnectTimeout() > 0 || paramsModel.getReadTimeout() > 0 || paramsModel.getWriteTimeout() > 0) {
            OkHttpClient.Builder builder = okHttpClient.newBuilder();
            builder.connectTimeout(paramsModel.getConnectTimeout() > 0 ? paramsModel.getConnectTimeout() : VokConfig.get().getConnectTimeout(), TimeUnit.MILLISECONDS);
            builder.readTimeout(paramsModel.getReadTimeout() > 0 ? paramsModel.getReadTimeout() : VokConfig.get().getReadTimeout(), TimeUnit.MILLISECONDS);
            builder.writeTimeout(paramsModel.getWriteTimeout() > 0 ? paramsModel.getWriteTimeout() : VokConfig.get().getWriteTimeout(), TimeUnit.MILLISECONDS);
            okHttpClient = builder.build();
        }
        if (paramsModel.getClient() != null) {
            okHttpClient = paramsModel.getClient();
        }
        return okHttpClient.newCall(request);
    }

    @Override
    public Response syncExecute(ParamsModel paramsModel) {
        try {
            return buildSyncRequestAndExecute(paramsModel);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Response buildSyncRequestAndExecute(ParamsModel paramsModel) throws IOException {
        return buildCall(paramsModel).execute();
    }

}
