package top.yifan.service.Impl;

import top.yifan.dao.CrawlerDao;
import top.yifan.dao.HtmlParserDao;
import top.yifan.dao.Impl.CrawlerDaoImpl;
import top.yifan.dao.Impl.HtmlParserToChapterDaoImpl;
import top.yifan.entity.UrlBean;
import top.yifan.service.CrawlerService;

import java.util.Map;

/**
 * 章节爬取业务逻辑实现类
 *
 * @author star
 **/
public class ChapterCrawlerServiceImpl implements CrawlerService<Map<String, Object>> {
    /**
     * 网页抓取器
     */
    private CrawlerDao crawlerDao = null;

    public ChapterCrawlerServiceImpl() {
        //初始化爬取器
        this.crawlerDao = new CrawlerDaoImpl();
    }

    @Override
    public Map<String, Object> crawl(UrlBean urlBean) {
        // 爬取网页
        String htmlSource = this.crawlerDao.crawl(urlBean);
        // 初始化 html 解析器
        HtmlParserDao<Map<String, Object>> htmlParserDao = new HtmlParserToChapterDaoImpl();
        // 解析 html 内容
        Map<String, Object> chapterMap = htmlParserDao.parserHtmlSource(htmlSource);

        return chapterMap;
    }
}
