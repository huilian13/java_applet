package com.kevin.dao.Impl;

import com.kevin.dao.HtmlParserDao;
import com.kevin.entity.UrlBean;
import com.vaolan.parser.JsoupHtmlParser;
import com.vaolan.status.DataFormatStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018-06-17 8:29
 * 解析内容
 *
 * @author kevin
 **/
public class HtmlParserToContentDaoImpl implements HtmlParserDao<String>{
    @Override
    public String parserHtmlSource(String htmlSource) {
        //创建选择器
        List<String> selector=new ArrayList<>(1);
        //添加选择条件
        selector.add("div#content");
        //解析
        String source = JsoupHtmlParser.getNodeContentBySelector(htmlSource, selector, DataFormatStatus.TagAllContent, false).get(0);
        String content = JsoupHtmlParser.getCleanTxt(source).replace("&nbsp;&nbsp;&nbsp;&nbsp;", "\r\n").replace("(三七中文 www.37zw.net)","");
        return content;
    }

}
