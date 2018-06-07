package com.vvme.vok.interfaces;

import com.vvme.vok.chain.RequestBodyChain;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 16:01.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IRequestBodyChain<T> {

    T requestBodyChain(RequestBodyChain requestBodyChain);

}
