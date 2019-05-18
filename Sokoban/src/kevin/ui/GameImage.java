package kevin.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * ��ϷͼƬ
 * @author kevin
 *
 */
public class GameImage {

	/**
	 * ��Ϸ����ͼ
	 */
	public static Image BG_IMG;
	
	
	/**
	 * ��Ϸͼ��
	 */
	public static Image TITLE;
	
	/**
	 * ��������ͼ
	 */
	public static Image WOLF_B;
	
	/**
	 * ���ﱳ��ͼ
	 */
	public static Image WOLF_A;
	
	/**
	 * ��������ͼ
	 */
	public static Image WOLF_L;
	
	/**
	 * ��������ͼ
	 */
	public static Image WOLF_R;
	
	/**
	 * ����ͼ
	 */
	public static Image BOX;
	
	/**
	 * ����
	 */
	public static Image TARGET;
	
	/**
	 * �ϰ������
	 */
	public static Image BLOCK;
	
	/**
	 * ��Ϸ������־
	 */
	public static Image OVER;
	
	/**
	 * ��ť
	 */
	public static ImageIcon BTN_IMG;
	
	/**
	 * ͼƬ·��
	 */
	public static final String GRAPHICS_PATH="graphics/";
	
	/**
	 * ������ϷƤ��
	 * @param path Ƥ��·��
	 */
	public static void setGameSkin(String path){
		//Ƥ��·��
		String skinPath=GRAPHICS_PATH+path;
		//��Ϸ����ͼ
		BG_IMG=new ImageIcon(skinPath+"/ground.png").getImage();
		//�ϰ������
		BLOCK=new ImageIcon(skinPath+"/block.png").getImage();
		//��Ϸ������־
		OVER=new ImageIcon(GRAPHICS_PATH+"/victory.png").getImage();
		//��Ϸͼ��
		TITLE=new ImageIcon(GRAPHICS_PATH+"/title.jpg").getImage();
		//��������ͼ
		WOLF_B=new ImageIcon(GRAPHICS_PATH+"/wolf_B.png").getImage();
		//���ﱳ��ͼ
		WOLF_A=new ImageIcon(GRAPHICS_PATH+"/wolf_A.png").getImage();
		//��������ͼ
		WOLF_L=new ImageIcon(GRAPHICS_PATH+"/wolf_L.png").getImage();
		//��������ͼ
		WOLF_R=new ImageIcon(GRAPHICS_PATH+"/wolf_R.png").getImage();
		//����ͼ
		BOX=new ImageIcon(GRAPHICS_PATH+"/sheep.png").getImage();
		//����
		TARGET=new ImageIcon(GRAPHICS_PATH+"/target.png").getImage();
		//�رհ�ť
		BTN_IMG=new ImageIcon(GRAPHICS_PATH+"/exit.png");
	}
	
	static{
		//��ʼ����ϷƤ��
		setGameSkin("newSkin1");
	}
}
