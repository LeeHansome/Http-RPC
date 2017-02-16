package com.hsm.rpc.proxy;

import com.hsm.rpc.container.Container;
import com.hsm.rpc.container.HttpContainer;
import com.hsm.rpc.exception.RpcException;
import com.hsm.rpc.exception.RpcExceptionCodeEnum;
import com.hsm.rpc.invoke.HttpInvoker;
import com.hsm.rpc.invoke.Invoker;
import com.hsm.rpc.invoke.ProviderConfig;
import com.hsm.rpc.serialize.Formater;
import com.hsm.rpc.serialize.Parser;
import com.hsm.rpc.serialize.Requeset;
import com.hsm.rpc.serialize.json.JsonFormater;
import com.hsm.rpc.serialize.json.JsonParser;
import org.mortbay.jetty.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yongzhangli on 14/2/17.
 */
public class ProviderProxyFactory extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProviderProxyFactory.class);
    private static ProviderProxyFactory factory;
    private Map<Class,Object> providers = new ConcurrentHashMap<Class, Object>();
    private Parser parser = new JsonParser();
    private Formater formater = new JsonFormater();
    private Invoker invoker = new HttpInvoker();

    public ProviderProxyFactory(Map<Class, Object> providers) {
       if(Container.container == null){
           new HttpContainer(this).start();
       }
       for(Map.Entry<Class,Object> entry : providers.entrySet()){
           register(entry.getKey(),entry.getValue());
       }
       factory=this;
    }

    public ProviderProxyFactory(Map<Class, Object> providers, ProviderConfig providerConfig) {
        if(Container.container == null){
            new HttpContainer(this,providerConfig).start();
        }
        for(Map.Entry<Class,Object> entry : providers.entrySet()){
            register(entry.getKey(),entry.getValue());
        }
        factory=this;
    }

    private void register(Class key, Object value) {
        providers.put(key,value);
        logger.info("{} 已经发布",key.getSimpleName());
    }


    public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        String reqStr = httpServletRequest.getParameter("data");
        try{
            Requeset rpcRequest = parser.reqParse(reqStr);
            Object result = rpcRequest.invoke(ProviderProxyFactory.getInstance().getBeanByClass(rpcRequest.getClazz()));
            invoker.response(formater.rspFormat(result),httpServletResponse.getOutputStream());
        }catch (RpcException e){
            e.printStackTrace();
        }catch (Throwable t){
            t.printStackTrace();
        }

    }

    public Object getBeanByClass(Class clazz) throws RpcException{
        Object bean = providers.get(clazz);
        if(bean != null){
            return bean;
        }
        throw new RpcException(RpcExceptionCodeEnum.NO_BEAN_FOUND.getCode(),clazz);
    }

    public static ProviderProxyFactory getInstance(){
        return factory;
    }
}
