package top.yifan.constant;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏图片路径
 *
 * @author star
 */
public final class GameImagePathConstant {
    /**
     * 私有化构造函数
     */
    private GameImagePathConstant() {
    }

    /**
     * 背景图
     */
    public static final String BACKGROUND = "graphics/background/game_map.png";
    public static final String PAUSE = "graphics/background/game_pause.png";
    public static final String GAME_OVER = "graphics/background/game_over.png";

    /**
     * 我方飞机图
     */
    public static final String HERO = "graphics/plane/hero/airplane.png";
    public static final String HERO_PLUS = "graphics/plane/hero/airplane_plus.png";

    /**
     * 敌机图片
     */
    public static final String ENEMY_SMALL = "graphics/plane/enemy/enemy_small.png";
    public static final String ENEMY_BIG = "graphics/plane/enemy/enemy_big.png";
    public static final String ENEMY_BOOS = "graphics/plane/enemy/enemy_boos.png";

    /**
     * 子弹图片
     */
    public static final String BULLET = "graphics/plane/bullet.png";

    /**
     * 开始图片
     */
    public static final String HOME = "graphics/other/home.png";

    /**
     * 开始按钮图片
     */
    public static final String START_BUTTON = "graphics/other/start_button.png";

    /**
     * 返回按钮图片
     */
    public static final String BACK_BUTTON = "graphics/other/back_button.png";

    /**
     * 生命图片
     */
    public static final String LIFE = "graphics/plane/life.png";

    /**
     * 爆炸效果图 TODO 修改
     */
    public static Image[] BOMBS = null;

    static {
        // 静态初始化爆炸效果图数组
        BOMBS = new Image[3];
        for (int i = 0; i < BOMBS.length; i++) {
            BOMBS[i] = new ImageIcon("graphics/bomb/bomb_" + i + ".gif").getImage();
        }
    }

}
