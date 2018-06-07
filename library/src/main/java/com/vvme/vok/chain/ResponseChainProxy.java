package com.vvme.vok.chain;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.vvme.vok.utils.ResponseHelper;
import com.vvme.vok.type.ResponseType;
import com.vvme.vok.config.VokConfig;
import com.vvme.vok.exception.VokException;
import com.vvme.vok.exception.VokRequestCanceledException;
import com.vvme.vok.exception.VokRequestFailedException;
import com.vvme.vok.model.ParamsModel;
import com.vvme.vok.response.ResponseParser;
import com.vvme.vok.response.ResponseProcessor;
import com.vvme.vok.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 12:32.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class ResponseChainProxy extends ResponseParser implements ResponseProcessor {

    private final ParamsModel mParamsModel;

    private ResponseChainProxy(ParamsModel paramsModel) {
        this.mParamsModel = paramsModel;
    }

    public static ResponseChainProxy get(ParamsModel paramsModel) {
        return new ResponseChainProxy(paramsModel);
    }

    public void processResponse(Call call, Response response) {
        if (call != null) {
            processRealResponse(call, response, mParamsModel);
        } else {
            //fake data processing
            processFakeResponse(response, mParamsModel);
        }
    }

    private void processRealResponse(Call call, Response response, ParamsModel paramsModel) {
        if (call.isCanceled()) {
            ResponseHelper.sendError(new VokRequestCanceledException(), paramsModel.getCallback());
            return;
        }
        if (!response.isSuccessful()) {
            ResponseHelper.sendError(new VokRequestFailedException("code: " + response.code()), paramsModel.getCallback());
            return;
        }
        //process result
        try {
            if (paramsModel.getResponseProcessor() != null) {
                paramsModel.getResponseProcessor().onResponse(paramsModel.getId(), response);
            } else {
                onResponse(paramsModel.getId(), response);
            }
            Object result = null;
            Type type = Utils.getGenericType(paramsModel.getCallback(), 0);
            if (paramsModel.getResponseParser() != null) {
                type = Utils.getGenericType(paramsModel.getResponseParser(), 0);
                result = paramsModel.getResponseParser().parseResponse(paramsModel, response, type);
            } else if (VokConfig.get().getResponseParser() != null) {
                result = VokConfig.get().getResponseParser().parseResponse(paramsModel, response, type);
            } else {
                result = parseResponse(paramsModel.getId(), response, type);
                Log.d("hate", result.toString());
            }
            ResponseHelper.sendSuccess(result, paramsModel.getCallback());
        } catch (Exception e) {
            e.printStackTrace();
            ResponseHelper.sendError(new VokException(e.getMessage()), paramsModel.getCallback());
        } finally {
            if (response.body() != null) {
                try {
                    response.body().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void processFakeResponse(Response response, ParamsModel paramsModel) {
        //process result
        try {
            if (paramsModel.getResponseProcessor() != null) {
                paramsModel.getResponseProcessor().onResponse(paramsModel.getId(), response);
            } else {
                onResponse(paramsModel.getId(), response);
            }
            Object result = null;
            Type type = Utils.getGenericType(paramsModel.getCallback(), 0);
            if (paramsModel.getResponseParser() != null) {
                result = paramsModel.getResponseParser().parseResponse(paramsModel, response, type);
            } else if (VokConfig.get().getResponseParser() != null) {
                result = VokConfig.get().getResponseParser().parseResponse(paramsModel, response, type);
            } else {
                result = parseResponse(paramsModel.getId(), response, type);
                Log.d("Vok", result.toString());
            }
            ResponseHelper.sendSuccess(result, paramsModel.getCallback());
        } catch (Exception e) {
            e.printStackTrace();
            ResponseHelper.sendError(new VokException(e.getMessage()), paramsModel.getCallback());
        } finally {
            if (response.body() != null) {
                try {
                    response.body().close();
                } catch (Exception e) {
                    e.printStackTrace();
                    ResponseHelper.sendError(e, paramsModel.getCallback());
                }
            }
        }
    }

    @Override
    public void onResponse(int id, Response response) {

    }

    @Override
    public Object parseResponse(int id, Response response, Type type) {
        //parse result
        ResponseType responseType = mParamsModel.getResponseType();
        Object result = null;
        try {
            if (responseType.isString()) {
                Log.d("Vok", "parse result type is String");
                result = response.body().string();
            } else if (responseType.isBitmap()) {
                Log.d("Vok", "parse result type is Bitmap");
                result = decodeBitmap(response);
            } else if (responseType.isFile()) {
                Log.d("Vok", "parse result type is File");
                result = saveFile(id, response, mParamsModel);
            } else if (responseType.isJson()) {
                Log.d("Vok", "parse result type is Json");
                Gson gson = new Gson();
                result = gson.fromJson(response.body().string(), type);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResponseHelper.sendError(e, mParamsModel.getCallback());
        }
        return result;
    }

    private Bitmap decodeBitmap(Response response) {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

    private File saveFile(final int id, Response response, final ParamsModel paramsModel) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;
            File dir = new File(paramsModel.getFilePath());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = paramsModel.getFileName();
            HttpUrl url = response.request().url();
            if (url != null) {
                String path = url.url().getPath();
                if (path != null) {
                    //get the file suffix.
                    String fileSuffix = path.substring(path.lastIndexOf("."));
                    if (fileName != null && !fileName.isEmpty()) {
                        fileName = fileName.trim();
                        if (!fileName.contains(".") || fileName.lastIndexOf(".") == fileName.length() - 1) {
                            //no setting the file suffix.
                            if (fileName.indexOf(".") == fileName.length() - 1) {
                                fileName = System.currentTimeMillis() + fileSuffix;
                            } else {
                                fileName = fileName.concat(fileSuffix);
                            }
                            Log.d("Vok", "set the file suffix is: " + fileSuffix);
                        }
                    } else {
                        fileName = System.currentTimeMillis() + fileSuffix;
                    }
                }
            }
            if (fileName == null || fileName.isEmpty()) {
                fileName = System.currentTimeMillis() + ".vok.download";
            }
            File file = new File(dir, fileName);
            if (file.exists()) {
                boolean del = file.delete();
                Log.d("Vok", "delete file result is: " + del);
            }
            fos = new FileOutputStream(file);
            final long[] startTime = {System.currentTimeMillis()};
            final long duration = paramsModel.getProgressPublishTime();
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                if (paramsModel.getProgressCallback() != null) {
                    if (duration <= 0) {
                        ResponseHelper.post(new Runnable() {
                            @Override
                            public void run() {
                                paramsModel.getProgressCallback().progress(id, total, finalSum, finalSum * 100.0f / total);
                            }
                        });
                    } else {
                        if (System.currentTimeMillis() - startTime[0] >= duration) {
                            startTime[0] = System.currentTimeMillis();
                            ResponseHelper.post(new Runnable() {
                                @Override
                                public void run() {
                                    paramsModel.getProgressCallback().progress(id, total, finalSum, finalSum * 100.0f / total);
                                }
                            });
                        }
                    }
                }
            }
            fos.flush();
            return file;
        } finally {
            try {
                response.body().close();
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                ResponseHelper.sendError(e, paramsModel.getCallback());
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                ResponseHelper.sendError(e, paramsModel.getCallback());
            }
        }
    }

    public Object outCallParserResponse(Response response, Type type) {
        return parseResponse(mParamsModel.getId(), response, type);
    }

}
