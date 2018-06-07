package com.vvme.vok.exception;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Author: hcw
 * Date 2018/3/6
 */


public class VokException extends Exception {

    public VokException() {
        super();
    }

    public VokException(String s) {
        super(s);
    }

    public VokException(Throwable cause) {
        super(cause);
    }

    public VokException(String message, Throwable cause) {
        super(message, cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public VokException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
