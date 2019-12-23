package top.yifan.service.Impl;

import top.yifan.dao.CrawlerDao;
import top.yifan.dao.HtmlParserDao;
import top.yifan.dao.Impl.CrawlerDaoImpl;
import top.yifan.dao.Impl.HtmlParserToNovelDaoImpl;
import top.yifan.entity.Novel;
import top.yifan.entity.UrlBean;
import top.yifan.service.CrawlerService;

import java.util.List;

/**
 * 业务逻辑实现类
 *
 * @author star
 **/
public class NovelCrawlerServiceImpl implements CrawlerService<List<Novel>> {
    /**
     * 网页抓取器
     */
    private CrawlerDao crawlerDao = null;

    public NovelCrawlerServiceImpl() {
        // 初始化网页爬取器
        this.crawlerDao = new CrawlerDaoImpl();
    }

    @Override
    public List<Novel> crawl(UrlBean urlBean) {
        // 爬取网页内容
        String content = this.crawlerDao.crawl(urlBean);
        // 创建 html 解析器
        HtmlParserDao<List<Novel>> htmlParserDao = new HtmlParserToNovelDaoImpl();
        // 解析 html 数据
        List<Novel> novelList = htmlParserDao.parserHtmlSource(content);
        return novelList;

    }
}
