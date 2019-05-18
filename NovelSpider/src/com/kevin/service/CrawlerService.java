package com.kevin.service;

import com.kevin.entity.UrlBean;

import java.util.List;

/**
 * 2018-06-14 18:44
 * 小说爬取业务逻辑接口
 *
 * @author kevin
 **/
public interface CrawlerService <T>{
    /**
     * 爬取内容
     */
    T crawl(UrlBean urlBean);

}
