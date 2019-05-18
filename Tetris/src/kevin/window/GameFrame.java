package kevin.window;

import javax.swing.JFrame;

import kevin.config.GameConfig;
import kevin.utils.FrameUtil;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
    
	public GameFrame(GamePanel gamePanel){
		//设置标题
		this.setTitle(GameConfig.getFrameConfig().getTitle());
		//设置窗口可见
		this.setVisible(true);
		//设置默认面板
		this.setContentPane(gamePanel);
		//设置窗口大小并居中
		FrameUtil.frameCenter(GameConfig.getFrameConfig().getWidth(),GameConfig.getFrameConfig().getHeight(),this);
		//关闭窗口时退出jvm
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
