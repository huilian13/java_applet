package top.yifan.factory;

import top.yifan.config.GameConfig;

/**
 * 游戏工厂类
 *
 * @author star
 */
public class GameFactory {

    // 私有化构造函数
    private GameFactory() {
    }

    /**
     * 方块的数目
     */
    private static int rectNumber;

    static {
        try {
            // 初始化方块数目
            rectNumber = GameConfig.getSystemConfig().getRectPoints().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getRectNumber() {
        return rectNumber;
    }

}
