package top.yifan.utils;

import javax.swing.*;
import java.awt.*;

/**
 * 窗口工具类
 *
 * @author star
 */
public final class FrameUtil {

    private FrameUtil() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * 设置窗口大小并居中
     *
     * @param width  窗口的宽度
     * @param height 窗口的高度
     * @param frame
     */
    public static void frameCenter(int width, int height, JFrame frame) {
        // 获取屏幕获取工具
        Toolkit tool = Toolkit.getDefaultToolkit();
        // 获取屏幕像素
        Dimension screenSize = tool.getScreenSize();
        // 获取屏幕的宽度和高度
        int x = (int) screenSize.getWidth();
        int y = (int) screenSize.getHeight();
        // 设置窗口居中
        frame.setBounds(x - width >> 1, (y - height >> 1) - 32, width, height);
        // 窗口不可调
        frame.setResizable(false);
    }
}
