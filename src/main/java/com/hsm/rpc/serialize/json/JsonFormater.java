package com.hsm.rpc.serialize.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hsm.rpc.serialize.Formater;
import com.hsm.rpc.serialize.Requeset;

/**
 * Created by yongzhangli on 13/2/17.
 */
public class JsonFormater implements Formater{

    public static final Formater fomater = new JsonFormater();

    public String reqFormat(Class clazz, String method, Object param) {
        Requeset requeset = new Requeset();
        requeset.setClazz(clazz);
        requeset.setMethod(method);
        requeset.setParam(param);
        return JSON.toJSONString(requeset, SerializerFeature.WriteClassName);
    }

    public String rspFormat(Object param) {
        return JSON.toJSONString(param,SerializerFeature.WriteClassName);
    }
}
