package top.yifan.entity.plane;

import top.yifan.entity.AbstractFlyingObject;
import top.yifan.entity.Bullet;

import java.util.List;

/**
 * 飞机
 *
 * @author star
 */
public abstract class Plane extends AbstractFlyingObject {
    /**
     * 子弹集合
     */
    protected List<Bullet> bullets;

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

    public List<Bullet> getBullets() {
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

    public void lifeDown() {
        this.lifeValue--;
    }
}
