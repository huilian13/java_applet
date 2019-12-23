package top.yifan.service;


import top.yifan.entity.UrlBean;

/**
 * 小说爬取业务逻辑接口
 *
 * @author star
 **/
public interface CrawlerService<T> {
    /**
     * 爬取内容
     */
    T crawl(UrlBean urlBean);

}
