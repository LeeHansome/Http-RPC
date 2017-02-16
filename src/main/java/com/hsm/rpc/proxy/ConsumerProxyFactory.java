package com.hsm.rpc.proxy;

import com.hsm.rpc.invoke.ConsumerConfig;
import com.hsm.rpc.invoke.HttpInvoker;
import com.hsm.rpc.invoke.Invoker;
import com.hsm.rpc.serialize.Formater;
import com.hsm.rpc.serialize.Parser;
import com.hsm.rpc.serialize.json.JsonFormater;
import com.hsm.rpc.serialize.json.JsonParser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yongzhangli on 14/2/17.
 */
public class ConsumerProxyFactory implements InvocationHandler{
    private ConsumerConfig consumerConfig;
    private Parser parser = new JsonParser();
    private Formater formater = new JsonFormater();
    private Invoker invoker = new HttpInvoker();
    private String clazz;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class interfaceClass = proxy.getClass().getInterfaces()[0];
        String req = formater.reqFormat(interfaceClass,method.getName(),args[0]);
        String resp = invoker.request(req,consumerConfig);
        return parser.rspParse(resp);
    }

    public ConsumerConfig getConsumerConfig() {
        return consumerConfig;
    }

    public void setConsumerConfig(ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public Formater getFormater() {
        return formater;
    }

    public void setFormater(Formater formater) {
        this.formater = formater;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
