package com.vvme.vok.interfaces;

import java.util.Map;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/5 19:09.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IHeaders<T> {
    T header(String key, String value);

    T headers(Map<String, String> headers);
}
