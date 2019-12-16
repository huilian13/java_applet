package top.yifan.config;

import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置
 *
 * @author star
 */
public class SystemConfig {
    /**
     * 闪烁值
     */
    private int spark;

    /**
     * 速度
     */
    private int speed;

    /**
     * 间隔时间
     */
    private long sleepTime;

    /**
     * 爆炸生命值
     */
    private int bomb_life;

    /**
     * 击中数
     */
    private int normalNum;

    /**
     * 路径值
     */
    private Map<String, String> pathMap;

    public SystemConfig(Element system) {
        // 获取参数值
        this.spark = Integer.parseInt(system.attributeValue("spark"));
        this.speed = Integer.parseInt(system.attributeValue("speed"));
        this.sleepTime = Long.parseLong(system.attributeValue("sleepTime"));
        this.bomb_life = Integer.parseInt(system.attributeValue("bomb_life"));
        this.normalNum = Integer.parseInt(system.attributeValue("normalNum"));
        // 获取所有 music 标签
        List musicPath = system.elements("music");
        // 获取所有音乐路径
        this.getMusicPath(musicPath);

    }

    /**
     * 获取音乐路径
     */
    private void getMusicPath(List<Element> musicPath) {
        // 初始化音乐路径集合
        this.pathMap = new HashMap<>();
        for (Element path : musicPath) {
            this.pathMap.put(path.attributeValue("key"), path.attributeValue("value"));
        }
    }

    public int getSpark() {
        return spark;
    }

    public int getSpeed() {
        return speed;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public int getBomb_life() {
        return bomb_life;
    }

    public int getNormalNum() {
        return normalNum;
    }

    public Map<String, String> getPathMap() {
        return pathMap;
    }
}
