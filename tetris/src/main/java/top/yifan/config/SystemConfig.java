package top.yifan.config;

import org.dom4j.Element;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 系统配置类
 *
 * @author kevin
 */
public class SystemConfig implements Serializable {

	private static final long serialVersionUID = 2002455594915584211L;

	/**
     * 地图最大x坐标
     */
    private int maxX;

    /**
     * 地图最大y坐标
     */
    private int maxY;

    /**
     * 升级行数
     */
    private int levelUp;

    /**
     * 方块元素集合
     */
    private List<Point[]> rectPoints;

    /**
     * 方块旋转类型集合
     */
    private List<Boolean> roundType;

    /**
     * 加分表集合
     */
    private HashMap<Integer, Integer> scoreList;

    public SystemConfig(Element system) {
        // 获取最大x坐标
        this.maxX = Integer.parseInt(system.attributeValue("maxX"));
        // 获取最大y坐标
        this.maxY = Integer.parseInt(system.attributeValue("maxY"));
        // 获取升级行数
        this.levelUp = Integer.parseInt(system.attributeValue("levelUp"));
        // 获取方块配置
        this.rectConfig(system);
        // 获取加分表配置
        this.scoreConfig(system);
    }

    /**
     * 加分配置
     *
     * @param system 标签对象
     */
    @SuppressWarnings("unchecked")
    private void scoreConfig(Element system) {
        // 初始化加分表
        this.scoreList = new HashMap<Integer, Integer>();
        // 获取plusScore标签
        List<Element> plusScore = system.elements("plusScore");
        // 消行数
        int line = 0;
        // 得分数
        int score = 0;
        for (Element e : plusScore) {
            // 获取消行数
            line = Integer.parseInt(e.attributeValue("line"));
            // 获取得分
            score = Integer.parseInt(e.attributeValue("score"));
            // 添加到加分表中
            this.scoreList.put(line, score);
        }

    }

    /**
     * 方块配置
     *
     * @param system 标签对象
     */
    @SuppressWarnings("unchecked")
    private void rectConfig(Element system) {
        // 获取所有的rect元素
        List<Element> rectConfig = system.elements("rect");
        // 初始化方块集合
        this.rectPoints = new ArrayList<Point[]>(rectConfig.size());
        // 初始化旋转类型集合
        this.roundType = new ArrayList<Boolean>();
        for (Element rect : rectConfig) {
            // 是否旋转
            this.roundType.add(Boolean.parseBoolean(rect.attributeValue("round")));
            // 通过 rect 标签获取所有的 point 标签
            List<Element> pointConfig = rect.elements("point");
            // 获取 points 集合的长度
            int len = pointConfig.size();
            // 创建 point 对象数组
            Point[] points = new Point[len];
            // 初始化 point 对象数组
            for (int j = 0; j < len; j++) {
                int x = Integer.parseInt(pointConfig.get(j).attributeValue("x"));
                int y = Integer.parseInt(pointConfig.get(j).attributeValue("y"));
                points[j] = new Point(x, y);
            }
            // 把 point 数组对象添加到集合
            this.rectPoints.add(points);

        }
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getLevelUp() {
        return levelUp;
    }

    public List<Point[]> getRectPoints() {
        return rectPoints;
    }

    public List<Boolean> getRoundType() {
        return roundType;
    }

    public HashMap<Integer, Integer> getScoreList() {
        return scoreList;
    }

}
