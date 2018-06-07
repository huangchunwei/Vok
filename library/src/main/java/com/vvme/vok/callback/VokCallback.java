package com.vvme.vok.callback;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 16:25.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public abstract class VokCallback<T> {

    public abstract void onStart();

    public abstract void onSuccess(T data);

    public abstract void onError(Throwable throwable);

    public abstract void onFailed(int code, String msg);

}
