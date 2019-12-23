package top.yifan.entity;

import top.yifan.config.GameConfig;

import java.awt.*;
import java.util.List;

/**
 * 游戏方块实体
 *
 * @author star
 */
public class GameRect {

    /**
     * 方块数组
     */
    private Point[] rectPoint;

    /**
     * 方块编号
     */
    private int rectCode;

    /**
     * 地图的边界大小
     */
    private static final int MIN_X = 0;

    private static final int MAX_X = GameConfig.getSystemConfig().getMaxX();

    private static final int MIN_Y = 0;

    private static final int MAX_Y = GameConfig.getSystemConfig().getMaxY();

    /**
     * 方块类型集合
     */
    private static final List<Point[]> RECT_TYPE = GameConfig.getSystemConfig().getRectPoints();

    /**
     * 旋转类型集合
     */
    private static final List<Boolean> ROUND_TYPE = GameConfig.getSystemConfig().getRoundType();

    public GameRect(int rectCode) {
        // 初始化方块
        this.initRect(rectCode);


    }

    /**
     * 创建方块
     */
    public void initRect(int rectCode) {
        // 存储方块编号
        this.rectCode = rectCode;
        // 初始化方块
        Point[] rect = RECT_TYPE.get(rectCode);
        // 初始化方块数组
        this.rectPoint = new Point[rect.length];
        for (int i = 0, length = rect.length; i < length; i++) {
            // 将 point 对象逐个克隆到方块数组中
            this.rectPoint[i] = (Point) rect[i].clone();
        }
    }

    /**
     * 方块移动
     */
    public boolean rectMove(int moveX, int moveY, boolean[][] gameMap) {
        // 声明单个方块对象，作为临时变量
        Point rect = null;
        // 单个方块新的x坐标，作为临时变量
        int newX = 0;
        // 单个方块新的y坐标，作为临时变量
        int newY = 0;
        // 移动处理，判断是否越界
        for (int i = 0, length = this.rectPoint.length; i < length; i++) {
            // 获取单个方块对象
            rect = rectPoint[i];
            // 单个方块新的x坐标
            newX = rect.x + moveX;
            // 单个方块新的y坐标
            newY = rect.y + moveY;
            // 边界判断
            if (this.isOverMap(newX, newY, gameMap)) {
                return false;
            }
        }

        // 方块可以移动
        for (int i = 0, length = this.rectPoint.length; i < length; i++) {
            rect = rectPoint[i];
            rect.x += moveX;
            rect.y += moveY;
        }
        return true;
    }

    /**
     * 方块旋转
     */
    public void rectRound(boolean[][] gameMap) {
        // 声明单个方块对象，作为临时变量
        Point rect = null;
        // 单个方块新的x坐标，作为临时变量
        int newX = 0;
        // 单个方块新的y坐标，作为临时变量
        int newY = 0;
        // 方块为黄色时不旋转
        if (!ROUND_TYPE.get(this.rectCode)) {
            return;
        }
        for (int i = 1, length = this.rectPoint.length; i < length; i++) {
            // 获取方块对象
            rect = this.rectPoint[i];
            // 方块新坐标
            newX = this.rectPoint[0].y + this.rectPoint[0].x - rect.y;
            newY = this.rectPoint[0].y - this.rectPoint[0].x + rect.x;
            // 边界判断
            if (this.isOverMap(newX, newY, gameMap)) {
                return;
            }
        }
        // 可以旋转
        for (int i = 1, length = this.rectPoint.length; i < length; i++) {
            // 获取方块对象
            rect = this.rectPoint[i];
            // 方块新坐标
            newX = this.rectPoint[0].y + this.rectPoint[0].x - rect.y;
            newY = this.rectPoint[0].y - this.rectPoint[0].x + rect.x;
            rect.x = newX;
            rect.y = newY;
        }
    }

    /**
     * 边界判断
     *
     * @param x 方块的 x 坐标
     * @param y 方块的 y 坐标
     * @return true 代表越界
     */
    private boolean isOverMap(int x, int y, boolean[][] gameMap) {
        return x < MIN_X || x >= MAX_X || y < MIN_Y || y >= MAX_Y || gameMap[y][x];
    }

    /**
     * 获取方块编号
     *
     * @return 编号
     */
    public int getRectCode() {
        return rectCode;
    }

    /**
     * 获取方块数组
     *
     * @return 数组对象
     */
    public Point[] getRectPoint() {
        return rectPoint;
    }

}

