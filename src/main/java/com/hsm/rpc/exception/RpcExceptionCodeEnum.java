package com.hsm.rpc.exception;

/**
 * Created by yongzhangli on 13/2/17.
 */
public enum RpcExceptionCodeEnum {
    DATA_PARSER_ERROR("DATA_PARSER_ERROR","数据解析出错"),
    NO_BEAN_FOUND("NO_BEAN_FOUND","没找到对应的Bean"),
    INVOKE_REQUEST_ERROR("INVOKE_REQUEST_ERROR","调用请求出错");

    private String code;
    private String msg;

    RpcExceptionCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
