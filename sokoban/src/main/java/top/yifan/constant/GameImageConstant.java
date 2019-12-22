package top.yifan.constant;

import top.yifan.util.FileLoadUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏图片
 *
 * @author star
 */
public class GameImageConstant {

    /**
     * 游戏背景图
     */
    public static Image BG_IMG;


    /**
     * 游戏图标
     */
    public static Image TITLE;

    /**
     * 人物正面图
     */
    public static Image WOLF_B;

    /**
     * 人物背面图
     */
    public static Image WOLF_A;

    /**
     * 人物左面图
     */
    public static Image WOLF_L;

    /**
     * 人物右面图
     */
    public static Image WOLF_R;

    /**
     * 箱子图
     */
    public static Image BOX;

    /**
     * 笼子
     */
    public static Image TARGET;

    /**
     * 障碍物（树）
     */
    public static Image BLOCK;

    /**
     * 游戏结束标志
     */
    public static Image OVER;

    /**
     * 按钮
     */
    public static ImageIcon BTN_IMG;

    /**
     * 图片路径
     */
    public static final String GRAPHICS_PATH = "graphics/";

    /**
     * 设置游戏皮肤
     *
     * @param path 皮肤路径
     */
    public static void setGameSkin(String path) {
        // 皮肤路径
        String skinPath = GRAPHICS_PATH + path;
        // 游戏背景图
        BG_IMG = FileLoadUtil.loadAsImage(skinPath + "/ground.png");
        // 障碍物（树）
        BLOCK = FileLoadUtil.loadAsImage(skinPath + "/block.png");
        // 游戏结束标志
        OVER = FileLoadUtil.loadAsImage(GRAPHICS_PATH + "/victory.png");
        // 游戏图标
        TITLE = FileLoadUtil.loadAsImage(GRAPHICS_PATH + "/title.jpg");
        // 人物正面图
        WOLF_B = FileLoadUtil.loadAsImage(GRAPHICS_PATH + "/wolf_B.png");
        // 人物背面图
        WOLF_A = FileLoadUtil.loadAsImage(GRAPHICS_PATH + "/wolf_A.png");
        // 人物左面图
        WOLF_L = FileLoadUtil.loadAsImage(GRAPHICS_PATH + "/wolf_L.png");
        // 人物右面图
        WOLF_R = FileLoadUtil.loadAsImage(GRAPHICS_PATH + "/wolf_R.png");
        // 箱子图
        BOX = FileLoadUtil.loadAsImage(GRAPHICS_PATH + "/sheep.png");
        // 笼子
        TARGET = FileLoadUtil.loadAsImage(GRAPHICS_PATH + "/target.png");
        // 关闭按钮
        BTN_IMG = new ImageIcon(GRAPHICS_PATH + "/exit.png");
    }

    static {
        // 初始化游戏皮肤
        setGameSkin("newSkin1");
    }
}
