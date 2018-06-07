package com.vvme.vok.chain;

import android.support.annotation.NonNull;

import java.util.Map;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 10:46.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface MergeParamsChain {

    String mergeParamsToUrl(@NonNull String url, Map<String, String> params);

}
