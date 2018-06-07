package com.vvme.vok.interfaces;

import com.vvme.vok.builder.BitmapRequestBuilder;
import com.vvme.vok.builder.FileRequestBuilder;
import com.vvme.vok.builder.JsonRequestBuilder;
import com.vvme.vok.builder.StringRequestBuilder;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 21:41.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface ResultType {

    StringRequestBuilder asString();

    BitmapRequestBuilder asBitmap();

    JsonRequestBuilder asJson();

    FileRequestBuilder asFile();


}
