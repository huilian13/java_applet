package kevin.ui;

import javax.swing.JFrame;

import kevin.config.GameConfig;
import kevin.util.FrameUtil;

/**
 * 游戏窗口类
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	/**
	 * 窗口的宽度
	 */
	private static final int WIDTH=GameConfig.getFrameConfig().getWidth();
	
	/**
	 * 窗口的高度
	 */
	private static final int HEIGHT=GameConfig.getFrameConfig().getHeight();
      
	public GameFrame(GamePanel gamePanel){
		//设置标题
		this.setTitle("贪吃蛇1.0");
		//设置窗口图标
		this.setIconImage(GameImage.WINDOW_ICON);
		//设置默认面板
		this.setContentPane(gamePanel);
		//设置窗口大小并居中
		FrameUtil.frameCenter(WIDTH,HEIGHT, this);
	    //显示窗口
   	    this.setVisible(true);
   	    //关闭窗口时退出jvm
     	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
