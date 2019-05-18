package kevin.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ��ϷͼƬ��
 * @author kevin
 *
 */
public class GameImage {

	/**
	 * ˽�л����캯��
	 */
	private GameImage(){}
	
	/**
	 * ����ͼ��
	 */
	protected static final Image WINDOW_ICON=new ImageIcon("graphics/snakeHead.png").getImage();
	
	/**
	 * ��ʼ��ť
	 */
	protected static final ImageIcon START_BUTTON=new ImageIcon("graphics/start.png");
	
	/**
	 * ���ð�ť
	 */
	protected static final ImageIcon CONFIG_BUTTON=new ImageIcon("graphics/config.png");
	
	/**
	 * ��ͣ��ť
	 */
	protected static final Image PAUSE=new ImageIcon("graphics/pause.png").getImage();
	
	
}
