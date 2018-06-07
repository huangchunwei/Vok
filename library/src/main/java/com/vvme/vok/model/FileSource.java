package com.vvme.vok.model;

import java.io.File;

/**
 * Project name:Vok
 * Author:VV
 * Created on 2018/6/7 10:28.
 * Copyright (c) 2018, vvismile@163.com All Rights Reserved.
 * Description: TODO
 */
public class FileSource {
    private String mKey;
    private String mFilename;
    private File mFile;

    public FileSource(String name, String fileName, File file) {
        this.mKey = name;
        this.mFilename = fileName;
        this.mFile = file;
    }

    public String getKey() {
        return mKey;
    }

    public String getFilename() {
        return mFilename;
    }

    public File getFile() {
        return mFile;
    }

    @Override
    public String toString() {
        return "FileSource{" +
                "mKey='" + mKey + '\'' +
                ", mFilename='" + mFilename + '\'' +
                ", mFile=" + mFile +
                '}';
    }
}
