package kevin.ui;

import javax.swing.JFrame;

import kevin.config.GameConfig;
import kevin.util.FrameUtil;
/**
 * 游戏窗口
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	
	public GameFrame(GamePanel gamePanel){
		//设置标题
		this.setTitle(GameConfig.getFrameConfig().getTitle());
		//设置图标
		this.setIconImage(GameImage.TITLE);
		//设置默认面板
		this.setContentPane(gamePanel);
		//设置窗口大小并居中
		FrameUtil.frameCenter(GameConfig.getFrameConfig().getWidth(),GameConfig.getFrameConfig().getHeight(),this);
		//去除窗口边框修饰
		this.setUndecorated(true);
		//设置窗口可以显示
		this.setVisible(true);
		//关闭时退出jvm
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
