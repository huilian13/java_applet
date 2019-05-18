package kevin.ui;

import javax.swing.JFrame;

import kevin.config.GameConfig;
import kevin.util.FrameUtil;

/**
 * ��Ϸ������
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	/**
	 * ���ڵĿ��
	 */
	private static final int WIDTH=GameConfig.getFrameConfig().getWidth();
	
	/**
	 * ���ڵĸ߶�
	 */
	private static final int HEIGHT=GameConfig.getFrameConfig().getHeight();
      
	public GameFrame(GamePanel gamePanel){
		//���ñ���
		this.setTitle("̰����1.0");
		//���ô���ͼ��
		this.setIconImage(GameImage.WINDOW_ICON);
		//����Ĭ�����
		this.setContentPane(gamePanel);
		//���ô��ڴ�С������
		FrameUtil.frameCenter(WIDTH,HEIGHT, this);
	    //��ʾ����
   	    this.setVisible(true);
   	    //�رմ���ʱ�˳�jvm
     	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
