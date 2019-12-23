package top.yifan.service.Impl;

import top.yifan.dao.CrawlerDao;
import top.yifan.dao.HtmlParserDao;
import top.yifan.dao.Impl.CrawlerDaoImpl;
import top.yifan.dao.Impl.HtmlParserToContentDaoImpl;
import top.yifan.entity.UrlBean;
import top.yifan.service.CrawlerService;

/**
 * 内容抓取器
 *
 * @author star
 **/
public class ContentCrawlerServiceImpl implements CrawlerService<String> {
    /**
     * 网页抓取器
     */
    private CrawlerDao crawlerDao = null;

    public ContentCrawlerServiceImpl() {
        // 初始化爬取器
        this.crawlerDao = new CrawlerDaoImpl();
    }

    @Override
    public String crawl(UrlBean urlBean) {
        // 爬取网页
        String htmlSource = this.crawlerDao.crawl(urlBean);
        // 解析器
        HtmlParserDao<String> parser = new HtmlParserToContentDaoImpl();
        // 解析网页
        String content = parser.parserHtmlSource(htmlSource);

        return content;
    }
}
