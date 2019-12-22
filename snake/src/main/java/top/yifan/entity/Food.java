package top.yifan.entity;

import java.awt.Point;


/**
 * 食物类
 *
 * @author star
 */
public class Food {

    /**
     * 食物的坐标
     */
    private Point foodPoint;

    public Food(Point foodPoint) {
        this.foodPoint = foodPoint;
    }

    public Point getfoodPoint() {
        return foodPoint;
    }

    public void setfoodPoint(Point foodPoint) {
        this.foodPoint = foodPoint;
    }
}

