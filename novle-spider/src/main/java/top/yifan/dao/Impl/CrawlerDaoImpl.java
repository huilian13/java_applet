package top.yifan.dao.Impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;
import top.yifan.dao.CrawlerDao;
import top.yifan.entity.UrlBean;
import top.yifan.factory.HttpClientFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * 爬虫功能实现类
 *
 * @author star
 **/
public class CrawlerDaoImpl implements CrawlerDao {

    @Override
    public String crawl(UrlBean urlBean) {
        // 响应对象
        CloseableHttpResponse response = null;
        try {
            // 创建请求
            RequestBuilder requestBuilder = RequestBuilder.get().setUri(new URI(urlBean.getUrl()));
            // 获取参数
            Map<String, String> params = urlBean.getParams();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    // 设置请求参数
                    requestBuilder.addParameter(param.getKey(), param.getValue());
                }
            }
            // 建立请求
            HttpUriRequest request = requestBuilder.build();
            // 执行请求,获取响应对象
            response = HttpClientFactory.getHttpClient().execute(request);
            // 获取响应实体
            String htmlContent = EntityUtils.toString(response.getEntity(), "gbk");
            return htmlContent;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭响应流
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
