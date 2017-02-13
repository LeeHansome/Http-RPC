package com.hsm.rpc.serialize.json;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSON;
import com.hsm.rpc.exception.RpcException;
import com.hsm.rpc.exception.RpcExceptionCodeEnum;
import com.hsm.rpc.serialize.Parser;
import com.hsm.rpc.serialize.Requeset;
import org.slf4j.LoggerFactory;

/**
 * Created by yongzhangli on 13/2/17.
 */
public class JsonParser implements Parser{

    private static final Logger logger = (Logger) LoggerFactory.getLogger(JsonParser.class);

    private static final Parser parser = new JsonParser();

    public Requeset reqParse(String param) throws RpcException{
        try {
            logger.debug("调用参数{}",param);
            return (Requeset) JSON.parse(param);
        }catch (Exception e){
            logger.error("转换异常{}",param,e);
            throw new RpcException("",e, RpcExceptionCodeEnum.DATA_PARSER_ERROR.getCode(),param);
        }
    }

    public <T> T rspParse(String result) {
        return (T) JSON.parse(result) ;
    }
}
