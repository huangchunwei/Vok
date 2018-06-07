package com.vvme.vok.interfaces;

import com.vvme.vok.response.FakeResponseBuilder;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 18:54.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IFakeDataDebug<T> {

    T openFakeData(boolean open);

    T fakeDataBuilder(FakeResponseBuilder fakeResponseBuilder);

}
