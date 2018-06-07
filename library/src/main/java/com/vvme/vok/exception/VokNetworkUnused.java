package com.vvme.vok.exception;


import com.vvme.vok.constants.TxtConstant;

/**
 * @author:hcw
 * @date 2018/3/6
 */


public class VokNetworkUnused extends VokException {

    public VokNetworkUnused() {
        super(TxtConstant.NETWORK_UNUSED);
    }
}
