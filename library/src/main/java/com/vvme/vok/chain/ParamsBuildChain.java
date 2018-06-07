package com.vvme.vok.chain;

import android.support.annotation.NonNull;

import com.vvme.vok.model.FileSource;

import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 10:44.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: 不需要手动调用这两个方法,系统会自动调用,只需要自己在这两个方法中处理逻辑即可
 * 第一个方法:是普通的post请求,第二个方法为混合参数post请求
 */
public interface ParamsBuildChain {

    void addParamsToRequestBody(@NonNull FormBody.Builder builder, Map<String, String> params);

    void addParamsToRequestBody(@NonNull MultipartBody.Builder builder, Map<String, String> params, List<FileSource> fileSources);

}
