package com.vvme.vok.utils;

import android.net.Uri;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Map;
import java.util.Set;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/4 16:26.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class Utils {

    public static String mergeParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }

    public static Class getGenericTypeClass(Object o, int index) {
        Type genType = o.getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        } else {
            ParameterizedType parameterizedType = (ParameterizedType) genType;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (index < 0 || index >= types.length) {
                throw new IllegalArgumentException(
                        "Index " + index + " not in range [0," + types.length + ") for " + parameterizedType);
            }
            Type paramType = types[index];
            if (!(paramType instanceof Class)) {
                return Object.class;
            }
            return ((Class) paramType);
        }
    }

    public static Type getGenericType(Object o, int index) throws Exception {
        Type genType = o.getClass().getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        } else {
            return getActualType(index, (ParameterizedType) genType);
        }
    }

    static Type getActualType(int index, ParameterizedType type) throws Exception {
        Type[] types = type.getActualTypeArguments();
        if (index < 0 || index >= types.length) {
            throw new IllegalArgumentException(
                    "Index " + index + " not in range [0," + types.length + ") for " + type);
        }
        Type paramType = types[index];
        if (paramType instanceof WildcardType) {
            return ((WildcardType) paramType).getUpperBounds()[0];
        }
        return paramType;
    }

}
