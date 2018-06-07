package com.vvme.vok.parser;

import com.vvme.vok.model.ParamsModel;

import java.lang.reflect.Type;

import okhttp3.Response;

public abstract class CustomResponseParser<T> {

    public abstract T parseResponse(ParamsModel paramsModel, Response response, Type type) throws Exception;

}