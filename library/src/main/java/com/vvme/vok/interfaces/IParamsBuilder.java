package com.vvme.vok.interfaces;

import com.vvme.vok.chain.ParamsBuildChain;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 13:05.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IParamsBuilder<T> {
    T paramsBuildChain(ParamsBuildChain paramsBuildChain);
}
