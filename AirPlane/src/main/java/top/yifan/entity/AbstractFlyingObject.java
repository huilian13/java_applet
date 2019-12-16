package top.yifan.entity;

/**
 * 飞行物
 *
 * @author star
 */
public abstract class AbstractFlyingObject implements Fly {

    /**
     * 飞行物 x 坐标
     */
    protected int x;

    /**
     * 飞行物 y 坐标
     */
    protected int y;

    /**
     * 飞行物宽度
     */
    protected int width;

    /**
     * 飞行物高度
     */
    protected int height;
    
    public AbstractFlyingObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
