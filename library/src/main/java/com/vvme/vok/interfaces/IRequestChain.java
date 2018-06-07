package com.vvme.vok.interfaces;

import com.vvme.vok.chain.CustomHeaderChain;
import com.vvme.vok.chain.CustomRequestChain;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 12:51.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IRequestChain<T> {

    T requestChain(CustomRequestChain requestChain);

    T requestHeaderChain(CustomHeaderChain headerChain);

}
