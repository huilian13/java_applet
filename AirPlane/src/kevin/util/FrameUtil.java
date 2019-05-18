package kevin.util;

import javax.swing.*;
import java.awt.*;

/**
 * 窗口工具类
 * @author kevin
 */
public class FrameUtil {
    /**
     * 居中设置窗口大小
     * @param width 窗口的宽度
     * @param height 窗口的高度
     * @param frame 窗口对象
     */
    public static void frameCenter(int width,int height,JFrame frame){
        //获取屏幕获取器
        Toolkit tool=Toolkit.getDefaultToolkit();
        //获取屏幕尺寸
        Dimension d=tool.getScreenSize();
        //获取屏幕的宽度
        int x=(int)d.getWidth();
        //获取屏幕的高度
        int y=(int)d.getHeight()-64;
        //设置窗口的位置
        frame.setBounds(x-width>>1,y-height>>1,width,height);
        //显示窗口
        frame.setVisible(true);
        //窗口不可调整
        frame.setResizable(false);
        //关闭窗口时退出jvm
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
