package com.kevin.service.Impl;

import com.kevin.dao.CrawlerDao;
import com.kevin.dao.HtmlParserDao;
import com.kevin.dao.Impl.CrawlerDaoImpl;
import com.kevin.dao.Impl.HtmlParserToChapterDaoImpl;
import com.kevin.entity.UrlBean;
import com.kevin.service.CrawlerService;

import java.util.Map;

/**
 * 2018-06-15 14:07
 * 章节爬取业务逻辑实现类
 *
 * @author kevin
 **/
public class ChapterCrawlerServiceImpl implements CrawlerService<Map<String,Object>> {
    /**
     * 网页抓取器
     */
    private CrawlerDao crawlerDao = null;

    public ChapterCrawlerServiceImpl() {
        //初始化爬取器
        this.crawlerDao = new CrawlerDaoImpl();
    }

    @Override
    public Map<String,Object> crawl(UrlBean urlBean) {
        //爬取网页
        String htmlSource = this.crawlerDao.crawl(urlBean);
        //初始化html解析器
        HtmlParserDao<Map<String ,Object>> htmlParserDao = new HtmlParserToChapterDaoImpl();
        //解析html内容
        Map<String, Object> chapterMap = htmlParserDao.parserHtmlSource(htmlSource);

        return chapterMap;
    }
}
