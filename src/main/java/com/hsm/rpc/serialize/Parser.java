package com.hsm.rpc.serialize;

import com.hsm.rpc.exception.RpcException;
import com.hsm.rpc.serialize.Requeset;

/**
 * Created by yongzhangli on 13/2/17.
 */
public interface Parser {
    Requeset reqParse(String param) throws RpcException;
    public <T> T rspParse(String result);
}
