package com.vvme.vok.chain;

import java.util.Map;

import okhttp3.Headers;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 11:00.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface CustomHeaderChain {

    Headers headers(Map<String, String> headers);
}
