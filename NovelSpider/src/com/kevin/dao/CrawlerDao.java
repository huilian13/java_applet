package com.kevin.dao;

import com.kevin.entity.UrlBean;

/**
 * 2018-06-14 15:00
 * 爬虫功能接口
 *
 * @author kevin
 **/
public interface CrawlerDao {
    /**
     * 网页爬取
     * @param urlBean 小说爬取地址
     */
     String crawl(UrlBean urlBean);
}
