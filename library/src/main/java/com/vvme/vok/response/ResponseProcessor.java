package com.vvme.vok.response;

import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 13:27.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface ResponseProcessor {
    void onResponse(int id, Response response);
}
