package com.hsm.rpc.serialize;

import java.io.Serializable;

/**
 * Created by yongzhangli on 13/2/17.
 */
public class Requeset implements Serializable{
    private Class clazz;
    private String method;
    private Object param;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
