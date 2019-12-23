package top.yifan.entity;

import java.util.Map;

/**
 * 网页链接类
 *
 * @author star
 **/
public class UrlBean {
    /**
     * 网页请求地址
     */
    private String url;

    /**
     * 请求参数
     */
    private Map<String, String> params;

    public UrlBean(String url) {
        this.url = url;
    }

    public UrlBean(String url, Map<String, String> params) {
        this.url = url;
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
