package top.yifan.util;

import top.yifan.config.GameConfig;

/**
 * CheckMapUtil
 *
 * @author star
 */
public final class CheckMapUtil {

    private CheckMapUtil() {

    }

    /**
     * x, y 坐标
     */
    private static final int minX = GameConfig.getSystemConfig().getMinX();

    private static final int maxX = GameConfig.getSystemConfig().getMaxX();

    private static final int minY = GameConfig.getSystemConfig().getMinY();

    private static final int maxY = GameConfig.getSystemConfig().getMaxY();

    /**
     * @param x       x 坐标
     * @param y       y 坐标
     * @return 返回 true，表示超出
     */
    public static boolean isOverMap(int x, int y) {
        return x <= minX || x >= maxX - 1 || y <= minY || y >= maxY - 1;
    }
}
