package top.yifan.entity.plane;


import top.yifan.constant.GameConstant;

import java.awt.*;

/**
 * 敌方飞机
 *
 * @author star
 */
public class EnemyPlane extends Plane {
    /**
     * 飞机图片
     */
    protected Image planeImg;

    /**
     * 分值
     */
    protected int scoreValue;

    public EnemyPlane(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean move(int moveX, int moveY) {
        int newY = this.y + moveY;
        if (newY == GameConstant.HEIGHT) {
            return false;
        }
        this.x += moveX;
        this.y += moveY;
        return true;
    }

    public Image getPlaneImg() {
        return planeImg;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
