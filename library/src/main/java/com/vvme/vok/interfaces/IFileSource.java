package com.vvme.vok.interfaces;

import com.vvme.vok.model.FileSource;

import java.util.List;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 16:16.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface IFileSource<T> {

    T fileSource(List<FileSource> fileSources);
    T fileSource(FileSource fileSource);

}
