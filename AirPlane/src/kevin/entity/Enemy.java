package kevin.entity;

import kevin.dto.Constant;

import java.awt.*;

/**
 * 敌方飞机
 * @author kevin
 */
public class Enemy extends Plane{
    /**
     * 飞机图片
     */
    protected Image planeImg;

    /**
     * 分值
     */
    protected int scoreValue;

    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    protected boolean move(int moveX, int moveY) {
        int newY=this.y+moveY;
        if(newY== Constant.HEIGHT){
            return false;
        }
        this.x+=moveX;
        this.y+=moveY;
        return true;
    }

    public Image getPlaneImg() {
        return planeImg;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
