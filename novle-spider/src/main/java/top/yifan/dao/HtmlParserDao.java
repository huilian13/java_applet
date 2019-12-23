package top.yifan.dao;

/**
 * html 解析接口
 *
 * @author star
 **/
public interface HtmlParserDao<T> {

    /**
     * 解析 html 内容
     *
     * @return
     */
    T parserHtmlSource(String htmlSource);

}
