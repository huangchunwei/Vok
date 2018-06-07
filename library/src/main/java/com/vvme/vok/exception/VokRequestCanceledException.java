package com.vvme.vok.exception;


import com.vvme.vok.constants.TxtConstant;

/**
 * @author: hcw
 * @date 2018/3/7
 */


public class VokRequestCanceledException extends VokException {
    public VokRequestCanceledException() {
        super(TxtConstant.REQUEST_CANCELED);
    }
}
