package top.yifan.entity;

import top.yifan.constant.FigureType;
import top.yifan.util.CheckMapUtil;

/**
 * 人物类（灰太狼）
 *
 * @author star
 */
public class Wolf {
    /**
     * X坐标
     */
    private int x;

    /**
     * Y坐标
     */
    private int y;

    public Wolf(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * 人物移动
     *
     * @param moveX x 轴偏移量
     * @param moveY y 轴偏移量
     */
    public void move(int moveX, int moveY, int[][] treeMap) {
        int newX = this.x + moveX;
        int newY = this.y + moveY;
        // 判断是否超出地图
        boolean isOverMap = CheckMapUtil.isOverMap(newX, newY);
        boolean isMeetBarrier = treeMap[newY][newX] == FigureType.BARRIER.getValue();
        // 如果超出边界并且遇到障碍物，则不移动
        if (isOverMap || isMeetBarrier) {
            return;
        }
        // 未超出边界，人物坐标改变
        this.x = this.x + moveX;
        this.y = this.y + moveY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
