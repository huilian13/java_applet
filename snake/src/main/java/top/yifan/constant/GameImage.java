package top.yifan.constant;

import top.yifan.util.FileLoadUtil;

import javax.swing.*;
import java.awt.*;


/**
 * 游戏图片类
 *
 * @author star
 */
public final class GameImage {

    /**
     * 私有化构造函数
     */
    private GameImage() {
    }

    /**
     * 窗口图标
     */
    public static final Image WINDOW_ICON = FileLoadUtil.loadAsImageIcon("graphics/snakeHead.png").getImage();

    /**
     * 开始按钮
     */
    public static final ImageIcon START_BUTTON = FileLoadUtil.loadAsImageIcon("graphics/start.png");

    /**
     * 设置按钮
     */
    public static final ImageIcon CONFIG_BUTTON = FileLoadUtil.loadAsImageIcon("graphics/config.png");

    /**
     * 暂停按钮
     */
    public static final Image PAUSE = FileLoadUtil.loadAsImageIcon("graphics/pause.png").getImage();


}
