package top.yifan.util;

import top.yifan.entity.AbstractFlyingObject;

/**
 * 碰撞算法
 */
public final class CrashUtil {

    private CrashUtil() {
    }

    /**
     * 碰撞方法
     *
     * @param fly    飞行物对象
     * @param x      x 坐标
     * @param y      y 坐标
     * @param width  碰撞物的宽度
     * @param height 碰撞物的高度
     * @return true 代表发生碰撞
     */
    public static boolean crash(AbstractFlyingObject fly, int x, int y, int width, int height) {
        int minX = fly.getX() - width;
        int maxX = fly.getX() + fly.getWidth();
        int minY = fly.getY() - height;
        int maxY = fly.getY() + fly.getHeight();
        // 判断是否发生碰撞
        if (x > minX && x < maxX && y > minY && y < maxY) {
            return true;
        }
        return false;
    }
}
