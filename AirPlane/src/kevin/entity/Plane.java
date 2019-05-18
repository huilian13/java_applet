package kevin.entity;

import java.util.ArrayList;

/**
 * 飞机类
 * @author kevin
 */
public abstract class Plane extends Flyer{
    /**
     * 子弹集合
     */
    protected ArrayList<Bullet> bullets;

    /**
     * 飞机存在标志
     */
    protected boolean isLive;

    /**
     * 生命值
     */
    protected int lifeValue;

    public Plane(int x, int y) {
        super(x, y);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getLifeValue() {
        return lifeValue;
    }

    /**
     * 移动行为
     * @param x x偏移量
     * @param y y偏移量
     * @return true表示可以移动
     */
    protected abstract boolean move(int moveX, int moveY);

}
