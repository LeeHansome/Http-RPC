package com.hsm.rpc.serialize;

/**
 * Created by yongzhangli on 13/2/17.
 */
public interface Formater {
    String reqFormat(Class clazz,String method,Object param);
    String rspFormat(Object param);
}
