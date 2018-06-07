package com.vvme.vok.callback;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/6 14:22.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public interface ProgressCallback {

    void progress(final int id, final long totalSize, final long currentSize, final float progress);

}
