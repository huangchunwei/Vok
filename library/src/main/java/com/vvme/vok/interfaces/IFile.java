package com.vvme.vok.interfaces;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 14:38.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IFile<T> extends IProgress<T>{

    T filePath(String filePath);

    T fileName(String fileName);

}
