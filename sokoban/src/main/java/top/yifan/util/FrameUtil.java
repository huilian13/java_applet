package top.yifan.util;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏窗口工具类
 *
 * @author star
 */
public final class FrameUtil {

    private FrameUtil() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * 窗口居中
     *
     * @param width  窗口宽度
     * @param height 窗口高度
     * @param frame  窗口对象
     */
    public static void frameCenter(int width, int height, JFrame frame) {
        // 获取系统工具类
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        // 获取屏幕尺寸获取器
        Dimension d = toolKit.getScreenSize();
        // 屏幕宽度
        int x = (int) d.getWidth();
        // 屏幕高度
        int y = (int) d.getHeight();
        // 设置窗口居中
        frame.setBounds(x - width >> 1, y - height >> 1, width, height);
        // 窗口不可调整
        frame.setResizable(false);
    }
}
