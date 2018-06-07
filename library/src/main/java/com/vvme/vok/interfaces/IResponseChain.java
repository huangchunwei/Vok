package com.vvme.vok.interfaces;

import com.vvme.vok.chain.CustomResponseChain;
import com.vvme.vok.parser.CustomResponseParser;
import com.vvme.vok.response.ResponseProcessor;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 16:50.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IResponseChain<T> {

    T responseChain(CustomResponseChain responseChain);

    T responseProcessor(ResponseProcessor responseProcessor);

    <C> T responseParser(CustomResponseParser<C> responseParser);

}
