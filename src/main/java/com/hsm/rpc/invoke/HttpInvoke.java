package com.hsm.rpc.invoke;

import com.hsm.rpc.exception.RpcException;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.OutputStream;

/**
 * Created by yongzhangli on 13/2/17.
 */
public class HttpInvoke implements Invoke{
    public String request(String request, ConsumerConfig consumerConfig) throws RpcException {
        return null;
    }

    public void response(String response, OutputStream outputStream) throws RpcException {

    }

    public static HttpClient getHttpClient(){
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        HttpHost localhost = new HttpHost("localhost",8080);
        cm.setMaxPerRoute(new HttpRoute(localhost),50);
        return HttpClients.custom()
                .setConnectionManager(cm).build();
    }
}
