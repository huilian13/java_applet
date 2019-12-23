package top.yifan.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * HTML 文本解析工具类
 *
 * @author star
 */
public final class JsoupHtmlParserUtil {

    private JsoupHtmlParserUtil() {

    }

    public static String getCleanTxt(String htmlSource) {
        return htmlSource == null || htmlSource.isEmpty() ? null : Jsoup.clean(htmlSource, Whitelist.none());
    }

    public static List<String> getNodeContentBySelector(String htmlSource, List<String> selectorList) {

        if (htmlSource == null || htmlSource.isEmpty() || selectorList == null || selectorList.isEmpty()) {
            return new ArrayList<>();
        }

        Document doc = Jsoup.parse(htmlSource);
        Iterator<String> selectorIteraotr = selectorList.iterator();
        String temp_selector = null;
        List<Element> temp_list_element = new LinkedList();
        Elements elements = null;
        Elements temp_elements = null;
        List<String> temp_list_line = new LinkedList();
        Document temp_doc = null;
        boolean isFirst = true;

        while (selectorIteraotr.hasNext()) {
            temp_selector = (String) selectorIteraotr.next();
            if (isFirst) {
                elements = doc.select(temp_selector);
                isFirst = false;
            } else {
                elements.clear();
                Iterator var14 = temp_list_line.iterator();

                while (var14.hasNext()) {
                    String line = (String) var14.next();
                    if (line != null && !line.isEmpty()) {
                        temp_doc = Jsoup.parse(line);
                        temp_elements = temp_doc.select(temp_selector);
                        if (temp_elements != null && !temp_elements.isEmpty()) {
                            elements.addAll(temp_elements);
                        }
                    }
                }
            }

            temp_list_element.clear();
            temp_list_element.addAll(elements);
            Iterator<Element> elementIteraotr = temp_list_element.iterator();
            temp_list_line.clear();

            while (elementIteraotr.hasNext()) {
                Element element = (Element) elementIteraotr.next();
                temp_list_line.add(element.toString());
            }
        }

        return temp_list_line;
    }

}
