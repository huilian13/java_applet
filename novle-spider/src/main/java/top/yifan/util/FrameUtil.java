package top.yifan.util;

import javax.swing.*;
import java.awt.*;

/**
 * 窗口设置工具类
 *
 * @author star
 **/
public final class FrameUtil {
    /**
     * 不允许实例化
     */
    private FrameUtil() {
    }

    /**
     * 窗口居中
     *
     * @param frame  窗口对象
     * @param width  窗口宽度
     * @param height 窗口高度
     */
    public static void frameCenter(JFrame frame, int width, int height) {
        // 屏幕像素获取器
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 获取屏幕像素
        Dimension screenSize = toolkit.getScreenSize();
        // 获取屏幕高度和宽度
        int x = screenSize.width;
        int y = screenSize.height;
        // 设置窗口大小和位置
        frame.setBounds(x - width >> 1, (y - height >> 1) - 64, width, height);
        // 设置窗口不可调
        frame.setResizable(false);
        // 显示窗口
        frame.setVisible(true);
        // 关闭窗口并退出 jvm
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
