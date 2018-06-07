package com.vvme.vok.exception;

/**
 * Timeout exception: connection timeout, read / write timeout, and so on.
 *
 * Author: hcw
 * Date 2017/11/24
 */


public class TimeoutException extends VokException {

    public TimeoutException(String s) {
        super(s);
    }
}
