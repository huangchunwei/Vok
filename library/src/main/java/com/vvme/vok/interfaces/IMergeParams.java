package com.vvme.vok.interfaces;

import com.vvme.vok.chain.MergeParamsChain;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 13:06.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IMergeParams<T> {

    T mergeParamsChain(MergeParamsChain mergeParamsChain);

}
