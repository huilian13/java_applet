package kevin.ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import kevin.factory.GameFactory;

public class GameImage {

	/**
	 * ˽�л����캯�����޷���������
	 */
	private GameImage(){}
	
	/**
	 * �㴰��ͼƬ
	 */
	public static Image WINDOW;
	
	/**
	 * ���ݿ�ͼƬ
	 */
	public static Image DATA;
	
	/**
	 * ���ؼ�¼ͼƬ
	 */
	public static Image DISK;
	
	/**
	 * �ȼ�ͼƬ
	 */
	public static Image RANK;
	
	/**
	 * ����ͼƬ
	 */
	public static Image RECT;
	
	/**
	 * ����
	 */
	public static  Image NUMBERS;
	
	/**
	 * ����
	 */
	public static  Image SCORE;
	
	/**
	 * ����
	 */
	public static  Image RMLINE;
	
	/**
	 * ����ͼ
	 */
	public static  Image NAME;
	
	/**
	 * ����ֵ��ͼƬ
	 */
	public static  Image RECT_EXP;
	
	/**
	 * ��ӰͼƬ
	 */
	public static  Image SHADOW;
	
	/**
	 * �ؼ�����ͼ
	 */
	public static  Image PSP;
	
	/**
	 * ��ͣͼ
	 */
	public static  Image PAUSE;
	
	/**
	 * ��ʼ��ť
	 */
	public static  ImageIcon START_BTN;
	
	/**
	 * ���ð�ť
	 */
	public static  ImageIcon SETTING_BTN;
	
	/**
	 * ��ʾ�����ͼƬ����
	 */
	public static  Image[] NEXT_RECT;
	
	/**
	 * ����ͼƬ
	 */
	public static  List<Image> BG_IMG;
	
	/**
	 * ͼƬ·��
	 */
	public static final String GRAPHICS_PATH="graphics/";
	
	/**
	 * Ĭ��·��
	 */
	private static final String DEFAULT_PATH="default";
	
	/**
	 * ������ϷƤ��
	 */
	public static void setGameSkin(String path){
		 //Ƥ��·��
		 String skinPath=GRAPHICS_PATH+path;
		
		 //�㴰��ͼƬ
		 WINDOW=new ImageIcon(skinPath+"/window/window.png").getImage();
		 //���ݿ�ͼƬ
		 DATA=new ImageIcon(skinPath+"/string/dataBase.png").getImage();
		 //���ؼ�¼ͼƬ
		 DISK=new ImageIcon(skinPath+"/string/disk.png").getImage();
		 //�ȼ�ͼƬ
		 RANK=new ImageIcon(skinPath+"/string/rank.png").getImage();
		 //����ͼƬ
		 RECT=new ImageIcon(skinPath+"/rectangle/rect.png").getImage();
		 //����
		 NUMBERS=new ImageIcon(skinPath+"/string/numbers.png").getImage();
		 //����
		 SCORE=new ImageIcon(skinPath+"/string/score.png").getImage();
		 //����ͼ
		 RMLINE=new ImageIcon(skinPath+"/string/rmline.png").getImage();
		 //����ͼ
		 NAME=new ImageIcon(skinPath+"/string/name.png").getImage();
		 //����ֵ��ͼƬ
		 RECT_EXP=new ImageIcon(skinPath+"/window/exp.png").getImage();
		 //��ӰͼƬ
		 SHADOW=new ImageIcon(skinPath+"/rectangle/shadow.png").getImage();
		 //�ؼ�����ͼ
		 PSP=new ImageIcon("data/psp.png").getImage();
		 //��ͣͼ
		 PAUSE=new ImageIcon(skinPath+"/string/pause.png").getImage();
		 //��ʼ��ť
		 START_BTN=new ImageIcon(skinPath+"/string/start.png");
		 //���ð�ť
		 SETTING_BTN=new ImageIcon(skinPath+"/string/config.png");
		 
		//��ʼ������ͼƬ����
		NEXT_RECT=new Image[GameFactory.getRectNumber()];
		for(int i=0,length=NEXT_RECT.length;i<length;i++){
			NEXT_RECT[i]=new ImageIcon(skinPath+"/rectangle/"+i+".png").getImage();
		}
		//��ʼ������ͼƬ����
		BG_IMG=new ArrayList<Image>();
		//�����ļ�����
		File file = new File(skinPath+"/background");
		//��ȡ�������ļ�
		File[] listFiles = file.listFiles();
		//�����������ļ�
		for(File f:listFiles){
			//�ж��Ƿ����ļ���
			if(!f.isDirectory()){
				//��ͼƬ�ļ���ӵ�����
				BG_IMG.add(new ImageIcon(f.getPath()).getImage());
			}
		}
	}
	
	static{
		
		setGameSkin(DEFAULT_PATH);
	}
}
