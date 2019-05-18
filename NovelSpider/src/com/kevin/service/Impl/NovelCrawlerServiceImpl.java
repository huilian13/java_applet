package com.kevin.service.Impl;

import com.kevin.dao.CrawlerDao;
import com.kevin.dao.HtmlParserDao;
import com.kevin.dao.Impl.CrawlerDaoImpl;
import com.kevin.dao.Impl.HtmlParserToNovelDaoImpl;
import com.kevin.dto.NovelSpiderDto;
import com.kevin.entity.Novel;
import com.kevin.entity.UrlBean;
import com.kevin.service.CrawlerService;

import java.util.HashMap;
import java.util.List;

/**
 * 2018-06-14 18:45
 * 业务逻辑实现类
 *
 * @author kevin
 **/
public class NovelCrawlerServiceImpl implements CrawlerService<List<Novel>>{
    /**
     * 网页抓取器
     */
    private CrawlerDao crawlerDao = null;

    public NovelCrawlerServiceImpl() {
        //初始化网页爬取器
        this.crawlerDao = new CrawlerDaoImpl();
    }

    @Override
    public List<Novel> crawl(UrlBean urlBean) {
        //爬取网页内容
        String content = this.crawlerDao.crawl(urlBean);
        //创建html解析器
        HtmlParserDao<List<Novel>> htmlParserDao = new HtmlParserToNovelDaoImpl();
        //解析html数据
        List<Novel> novelList =htmlParserDao.parserHtmlSource(content);
        return novelList;

    }
}
