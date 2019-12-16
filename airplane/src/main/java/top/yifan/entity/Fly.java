package top.yifan.entity;

/**
 * 飞行
 *
 * @author star
 */
public interface Fly {

    /**
     * 移动行为
     *
     * @param x x 偏移量
     * @param y y 偏移量
     * @return true 表示可以移动
     */
    boolean move(int x, int y);

}
