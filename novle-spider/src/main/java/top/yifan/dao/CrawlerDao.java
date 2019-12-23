package top.yifan.dao;


import top.yifan.entity.UrlBean;

/**
 * 爬虫功能接口
 *
 * @author star
 **/
public interface CrawlerDao {
    /**
     * 网页爬取
     *
     * @param urlBean 小说爬取地址
     */
    String crawl(UrlBean urlBean);
}
