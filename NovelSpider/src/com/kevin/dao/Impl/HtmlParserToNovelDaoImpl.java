package com.kevin.dao.Impl;

import com.kevin.dao.HtmlParserDao;
import com.kevin.entity.Novel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018-06-14 17:05
 * html解析实现类，将解析的数据封装成Novel对象
 *
 * @author kevin
 **/
public class HtmlParserToNovelDaoImpl implements HtmlParserDao<List<Novel>> {

    @Override
    public List<Novel> parserHtmlSource(String htmlSource) {
        //将网页内容解析成document对象
        Document document = Jsoup.parse(htmlSource);
        //获取指定元素
        Elements elements = document.select("div.novellist ul li");
        //小说对象集合
        List<Novel> novelList=new ArrayList<>(500);
        //小说对象
        Novel novel=null;
        for (Element e:elements){
            //创建小说对象
            novel=new Novel();
            //分割字符串
            String[] texts = e.text().split("/");
            //小说名
            novel.setNovelName(texts[0]);
            //小说作者
            novel.setNovelAuthor(texts[1]);
            //小说链接
            novel.setNovelUrl("https://www.37zw.net"+e.select("a").attr("href"));
            //添加到集合
            novelList.add(novel);
        }
        return novelList;
    }
}
