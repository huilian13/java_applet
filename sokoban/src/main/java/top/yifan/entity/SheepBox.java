package top.yifan.entity;

import top.yifan.constant.FigureType;
import top.yifan.util.CheckMapUtil;

/**
 * 箱子类（懒羊羊）
 *
 * @author star
 */
public class SheepBox {

    private int x;

    private int y;

    public SheepBox(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * 移动箱子
     *
     * @param moveX   x 坐标
     * @param moveY   y 坐标
     * @param treeMap 二维数组地图
     * @return
     */
    public boolean moveBox(int moveX, int moveY, int[][] treeMap) {
        int newX = this.x + moveX;
        int newY = this.y + moveY;
        // 判断超出地图边界
        boolean isOverMap = CheckMapUtil.isOverMap(newX, newY);
        // 判断箱子前面是否存在障碍物
        boolean isMeetBarrier = treeMap[newY][newX] == FigureType.BARRIER.getValue();
        // 判断箱子前面是否存在箱子
        boolean isBox = treeMap[newY][newX] == FigureType.BOX.getValue();
        // 如果超出边界并且遇到障碍物，则不移动
        if (isOverMap || isMeetBarrier || isBox) {
            return false;
        }

        this.x = this.x + moveX;
        this.y = this.y + moveY;
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
