package com.vvme.vok.builder;

import com.vvme.vok.type.RequestType;
import com.vvme.vok.model.ParamsModel;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 21:12.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class TypeRequestBuilder<T> extends ConfigRequestBuilder<T> {

    public TypeRequestBuilder() {
    }

    public TypeRequestBuilder(String url, RequestType type) {
        super(url, type);
    }

    public TypeRequestBuilder(ParamsModel.Builder builder) {
        super(builder);
    }
}
