package com.vvme.vok.interfaces;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 13:14.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IReqeustMethod<T, P> {

    T get();

    P post();

}
