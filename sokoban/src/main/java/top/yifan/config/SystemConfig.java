package top.yifan.config;

import org.dom4j.Element;

/**
 * 系统配置
 *
 * @author star
 */
public class SystemConfig {

    private int minX;

    /**
     * 最大 x 坐标
     */
    private int maxX;

    private int minY;

    /**
     * 最大 y 坐标
     */
    private int maxY;

    public SystemConfig(Element system) {
        // 获取 x 坐标值
        this.minX = Integer.parseInt(system.attributeValue("minX"));
        this.maxX = Integer.parseInt(system.attributeValue("maxX"));
        // 获取 y 坐标值
        this.minY = Integer.parseInt(system.attributeValue("minY"));
        this.maxY = Integer.parseInt(system.attributeValue("maxY"));
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

}
