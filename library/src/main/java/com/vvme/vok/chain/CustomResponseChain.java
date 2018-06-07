package com.vvme.vok.chain;

import com.vvme.vok.model.ParamsModel;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 17:01.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: Note that this interface needs to handle all the logic of response itself.
 * Returning to true is given to the system processing, and false represents its own processing.
 * Note that if you have written your own logic and then return to true at this time, your logic and system will be executed,
 * easily causing confusion, and suggest returning false if you have your own logic.
 */
public interface CustomResponseChain {

    boolean chain(Call call, Response response, ParamsModel paramsModel, boolean isFakeData);

}
