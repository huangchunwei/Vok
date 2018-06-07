package com.vvme.vok.interfaces;

import java.util.Map;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/5 19:09.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IParams<T> {

    T param(String key, String value);

    T params(Map<String, String> params);

}
