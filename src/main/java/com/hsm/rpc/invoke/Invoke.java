package com.hsm.rpc.invoke;

import com.hsm.rpc.exception.RpcException;

import java.io.OutputStream;

/**
 * Created by yongzhangli on 13/2/17.
 */
public interface Invoke {
    String request(String request,ConsumerConfig consumerConfig) throws RpcException;
    void response(String response, OutputStream outputStream) throws RpcException;
}
