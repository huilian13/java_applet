package top.yifan.dao.Impl;

import top.yifan.dao.HtmlParserDao;
import top.yifan.util.JsoupHtmlParserUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析内容
 *
 * @author star
 **/
public class HtmlParserToContentDaoImpl implements HtmlParserDao<String> {

    @Override
    public String parserHtmlSource(String htmlSource) {
        // 创建选择器
        List<String> selector = new ArrayList<>(1);
        // 添加选择条件
        selector.add("div#content");
        // 解析
        String source = JsoupHtmlParserUtil.getNodeContentBySelector(htmlSource, selector).get(0);
        String content = JsoupHtmlParserUtil.getCleanTxt(source)
                .replace("&nbsp;&nbsp;&nbsp;&nbsp;", "\r\n")
                .replace("(三七中文 www.37zw.net)", "");

        return content;
    }

}
