package com.kevin.service.Impl;

import com.kevin.dao.CrawlerDao;
import com.kevin.dao.HtmlParserDao;
import com.kevin.dao.Impl.CrawlerDaoImpl;
import com.kevin.dao.Impl.HtmlParserToContentDaoImpl;
import com.kevin.entity.UrlBean;
import com.kevin.service.CrawlerService;

/**
 * 2018-06-17 9:04
 * 内容抓取器
 *
 * @author kevin
 **/
public class ContentCrawlerServiceImpl implements CrawlerService<String> {
    /**
     * 网页抓取器
     */
    private CrawlerDao crawlerDao = null;

    public ContentCrawlerServiceImpl() {
        //初始化爬取器
        this.crawlerDao = new CrawlerDaoImpl();
    }

    @Override
    public String crawl(UrlBean urlBean) {
        //爬取网页
        String htmlSource = this.crawlerDao.crawl(urlBean);
        //解析器
        HtmlParserDao<String> parser = new HtmlParserToContentDaoImpl();
        //解析网页
        String content = parser.parserHtmlSource(htmlSource);

        return content;
    }
}
