package com.vvme.vok.interfaces;

import com.vvme.vok.callback.ProgressCallback;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 16:32.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IProgress<T> {
    
    T progressCallback(ProgressCallback callback);

}
