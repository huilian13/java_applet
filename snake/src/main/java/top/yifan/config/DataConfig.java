package top.yifan.config;

import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;

/**
 * 数据配置类
 *
 * @author star
 */
public class DataConfig {

    /**
     * 数据集合
     */
    private HashMap<String, String> dataMap;

    public DataConfig(Element data) {
        // 初始化数据集合
        this.dataMap = new HashMap<>();
        // 获取所有path的标签
        @SuppressWarnings("unchecked")
        List<Element> pathList = data.elements("path");
        // 添加到集合中
        for (Element e : pathList) {
            this.dataMap.put(e.attributeValue("key"), e.attributeValue("value"));
        }
    }

    public HashMap<String, String> getDataMap() {
        return dataMap;
    }

}
