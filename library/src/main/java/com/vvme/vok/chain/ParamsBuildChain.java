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
 * Description: Instead of calling these two methods manually,
 * the system will call automatically, only the first method is needed for yourself to process the logic in the two methods:
 * the ordinary post request, and the second method for the mixed parameter post request
 */
public interface ParamsBuildChain {

    void addParamsToRequestBody(@NonNull FormBody.Builder builder, Map<String, String> params);

    void addParamsToRequestBody(@NonNull MultipartBody.Builder builder, Map<String, String> params, List<FileSource> fileSources);

}
