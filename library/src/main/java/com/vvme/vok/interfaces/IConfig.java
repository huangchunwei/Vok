package com.vvme.vok.interfaces;

import okhttp3.OkHttpClient;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/5 19:10.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IConfig<T> {

    T client(OkHttpClient client);

    T connectTimeout(long time);

    T readTimeout(long time);

    T writeTimeout(long time);

}
