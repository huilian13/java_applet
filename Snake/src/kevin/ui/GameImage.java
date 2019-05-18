package kevin.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 游戏图片类
 * @author kevin
 *
 */
public class GameImage {

	/**
	 * 私有化构造函数
	 */
	private GameImage(){}
	
	/**
	 * 窗口图标
	 */
	protected static final Image WINDOW_ICON=new ImageIcon("graphics/snakeHead.png").getImage();
	
	/**
	 * 开始按钮
	 */
	protected static final ImageIcon START_BUTTON=new ImageIcon("graphics/start.png");
	
	/**
	 * 设置按钮
	 */
	protected static final ImageIcon CONFIG_BUTTON=new ImageIcon("graphics/config.png");
	
	/**
	 * 暂停按钮
	 */
	protected static final Image PAUSE=new ImageIcon("graphics/pause.png").getImage();
	
	
}
