package com.vvme.vok.chain;

import com.vvme.vok.model.ParamsModel;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 17:01.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: 注意这个接口需要自己处理response的所有逻辑,一般情况不建议自己处理,
 * 返回true表示交给系统处理,false表示自己处理.注意如果已经写有自己的逻辑,此时又返回true,
 * 则自己的逻辑和系统的都会执行,容易造成错乱,建议如果有自己的逻辑时返回false
 */
public interface CustomResponseChain {

    boolean chain(Call call, Response response, ParamsModel paramsModel, boolean isFakeData);

}
