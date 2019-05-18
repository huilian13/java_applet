package kevin.ui;

import javax.swing.JFrame;

import kevin.config.GameConfig;
import kevin.util.FrameUtil;
/**
 * ��Ϸ����
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	
	public GameFrame(GamePanel gamePanel){
		//���ñ���
		this.setTitle(GameConfig.getFrameConfig().getTitle());
		//����ͼ��
		this.setIconImage(GameImage.TITLE);
		//����Ĭ�����
		this.setContentPane(gamePanel);
		//���ô��ڴ�С������
		FrameUtil.frameCenter(GameConfig.getFrameConfig().getWidth(),GameConfig.getFrameConfig().getHeight(),this);
		//ȥ�����ڱ߿�����
		this.setUndecorated(true);
		//���ô��ڿ�����ʾ
		this.setVisible(true);
		//�ر�ʱ�˳�jvm
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
