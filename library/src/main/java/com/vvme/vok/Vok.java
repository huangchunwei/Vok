package com.vvme.vok;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import com.vvme.vok.builder.DownloadRequestBuilder;
import com.vvme.vok.builder.GetRequestBuilder;
import com.vvme.vok.builder.PostRequestBuilder;
import com.vvme.vok.builder.UploadRequestBuilder;
import com.vvme.vok.plateform.Platform;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 16:23.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description:
 * <p>
 * Vok
 * .get()/post()/download()/upload()
 * .with(context)/with(activity)/with(fragment)/with(support-fragment)
 * .param(key, value)/params(map)
 * .header(key, value)/headers(map)
 * .asString()/asBitmap()/asObject()/asFile()
 * .requestFilter(filter)
 * .responseConvert(convert)
 * .skipMemory(true/false)
 * .shouldCache(true/false)
 * .cacheStrategy(none/memory/disk)
 * .firstLoadCache(true/false)
 * .go(callback)
 * .syncGo() return response
 */
public final class Vok {

    private static final String TAG = Vok.class.getSimpleName();
    private static final Platform PLATFORM = Platform.get();

    public static void with() {

    }

    public static void with(Context context) {

    }

    public static void with(Activity activity) {

    }

    public static void with(Fragment fragment) {

    }

    public static void with(android.support.v4.app.Fragment fragment) {

    }

    public static GetRequestBuilder get(String url) {
        return new GetRequestBuilder(url);
    }

    public static PostRequestBuilder post(String url) {
        return new PostRequestBuilder(url);
    }

    public static DownloadRequestBuilder download(String url) {
        return new DownloadRequestBuilder(url);
    }

    public static UploadRequestBuilder upload(String url) {
        return new UploadRequestBuilder(url);
    }

    public static Platform platform() {
        return PLATFORM;
    }


}
