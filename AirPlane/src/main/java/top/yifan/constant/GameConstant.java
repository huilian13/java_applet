package top.yifan.constant;


import top.yifan.config.GameConfig;
import top.yifan.factory.GameImageFactory;

import java.util.Map;

/**
 * GameConstant
 *
 * @author star
 */
public final class GameConstant {

    /**
     * 私有化构造函数
     */
    private GameConstant() {
    }

    /**
     * 游戏窗口宽度
     */
    public static final int WIDTH = GameConfig.getFrameConfig().getWidth();

    /**
     * 闪烁值
     */
    public static final int SPARK_VALUE = GameConfig.getSystemConfig().getSpark();

    /**
     * 睡眠时间
     */
    public static final long SLEEP = GameConfig.getSystemConfig().getSleepTime();

    /**
     * 音乐路径集合
     */
    public static final Map<String, String> MUSIC_PATH_MAP = GameConfig.getSystemConfig().getPathMap();

    /**
     * 游戏窗口高度
     */
    public static final int HEIGHT = GameImageFactory.createImage(GameImagePathConstant.BACKGROUND).getHeight(null);

    /**
     * 子弹的宽度和高度
     */
    public static final int BULLET_WIDTH = GameImageFactory.createImage(GameImagePathConstant.BULLET).getWidth(null);
    public static final int BULLET_HEIGHT = GameImageFactory.createImage(GameImagePathConstant.BULLET).getHeight(null);

}
