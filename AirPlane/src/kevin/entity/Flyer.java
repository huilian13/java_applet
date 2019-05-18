package kevin.entity;

/**
 * 飞行物类
 * @author kevin
 */
public abstract class Flyer {
    /**
     * 飞行物的x坐标
     */
    protected int x;

    /**
     * 飞行物的y坐标
     */
    protected int y;

    /**
     * 飞机的宽度
     */
    protected int width;

    /**
     * 飞机的高度
     */
    protected int height;

    public Flyer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 移动行为
     * @param x x偏移量
     * @param y y偏移量
     * @return true表示可以移动
     */
    protected abstract boolean move(int x,int y);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
