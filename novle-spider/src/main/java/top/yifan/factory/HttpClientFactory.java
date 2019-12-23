package top.yifan.factory;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * httpClient 工厂类（负责 HttpClient 的供给，包括单例、线程池的管理）
 *
 * @author star
 **/
public class HttpClientFactory {
    /**
     * 不允许实例化
     */
    private HttpClientFactory() {
    }


    public static CloseableHttpClient getHttpClient() {
        return HttpClientHolder.INSTANCE;
    }

    private static class HttpClientHolder {
        /**
         * http 客户端
         */
        private static final CloseableHttpClient INSTANCE = HttpClients.createDefault();
    }
}
