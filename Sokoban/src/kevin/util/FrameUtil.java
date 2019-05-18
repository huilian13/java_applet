package kevin.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
/**
 * 窗口工具类
 * @author kevin
 *
 */
public class FrameUtil {
	
	/**
	 * 设置窗口的位置和大小
	 * @param width 窗口宽度
	 * @param height 窗口高度
	 * @param frame  窗口对象
	 */
	public static void frameCenter(int width,int height,JFrame frame){
		//获取屏幕获取器
		Toolkit toolKit=Toolkit.getDefaultToolkit();
		//获取屏幕的像素
		Dimension d=toolKit.getScreenSize();
		//获取屏幕的宽度
		int x=(int)d.getWidth();
		//获取屏幕的高度
		int y=(int)d.getHeight();
		//设置窗口大小并居中显示
		frame.setBounds(x-width>>1, y-height>>1, width, height);
		//设置窗口不可调
		frame.setResizable(false);
	}
}
