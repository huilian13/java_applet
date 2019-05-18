package com.kevin.dao;

/**
 * 2018-06-14 17:01
 * html解析接口
 *
 * @author kevin
 **/
public interface HtmlParserDao<T> {

    /**
     * 解析html内容
     * @return
     */
    T parserHtmlSource(String htmlSource);

}
