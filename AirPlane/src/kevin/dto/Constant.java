package kevin.dto;

import kevin.config.GameConfig;
import kevin.ui.GameImage;

import java.util.HashMap;

public class Constant {
    /**
     * 私有化构造函数
     */
    private Constant(){}

    /**
     * 游戏窗口宽度
     */
    public static final int WIDTH=GameConfig.getFrameConfig().getWidth();

    /**
     * 游戏窗口高度
     */
    public static final int HEIGHT=GameImage.BACKGROUND.getHeight(null);

    /**
     * 闪烁值
     */
    public static final int SPARK_VALUE= GameConfig.getSystemConfig().getSpark();


    /**
     * 子弹的宽度和高度
     */
    public static final int BULLET_WIDTH= GameImage.BULLET.getWidth(null);

    public static final int BULLET_HEIGHT=GameImage.BULLET.getHeight(null);

    /**
     * 睡眠时间
     */
    public static final long SLEEP=GameConfig.getSystemConfig().getSleepTime();

    /**
     * 音乐路径集合
     */
    public static final HashMap<String,String> PATH_MAP= GameConfig.getSystemConfig().getPathMap();

}
