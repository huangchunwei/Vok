package com.vvme.vok.exception;

/**
 * 超时异常:连接超时,读/写超时等.
 *
 * @author: hcw
 * @date 2017/11/24
 */


public class TimeoutException extends VokException {


    public TimeoutException(String s) {
        super(s);
    }
}
