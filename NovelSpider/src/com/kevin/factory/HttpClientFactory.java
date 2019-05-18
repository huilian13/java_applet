package com.kevin.factory;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 2018-06-14 19:23
 * httpClient工厂类（负责HttpClient的供给，包括单例、线程池的管理）
 * 饿汉单例
 * @author kevin
 **/
public class HttpClientFactory {
    /**
     * 不允许实例化
     */
    private HttpClientFactory(){}

    /**
     * http客户端
     */
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }
}
