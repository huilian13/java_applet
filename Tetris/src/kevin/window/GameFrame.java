package kevin.window;

import javax.swing.JFrame;

import kevin.config.GameConfig;
import kevin.utils.FrameUtil;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
    
	public GameFrame(GamePanel gamePanel){
		//���ñ���
		this.setTitle(GameConfig.getFrameConfig().getTitle());
		//���ô��ڿɼ�
		this.setVisible(true);
		//����Ĭ�����
		this.setContentPane(gamePanel);
		//���ô��ڴ�С������
		FrameUtil.frameCenter(GameConfig.getFrameConfig().getWidth(),GameConfig.getFrameConfig().getHeight(),this);
		//�رմ���ʱ�˳�jvm
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
