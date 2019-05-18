package kevin.ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import kevin.factory.GameFactory;

public class GameImage {

	/**
	 * 私有化构造函数，无法创建对象
	 */
	private GameImage(){}
	
	/**
	 * 层窗口图片
	 */
	public static Image WINDOW;
	
	/**
	 * 数据库图片
	 */
	public static Image DATA;
	
	/**
	 * 本地记录图片
	 */
	public static Image DISK;
	
	/**
	 * 等级图片
	 */
	public static Image RANK;
	
	/**
	 * 方块图片
	 */
	public static Image RECT;
	
	/**
	 * 数字
	 */
	public static  Image NUMBERS;
	
	/**
	 * 分数
	 */
	public static  Image SCORE;
	
	/**
	 * 消行
	 */
	public static  Image RMLINE;
	
	/**
	 * 名称图
	 */
	public static  Image NAME;
	
	/**
	 * 矩形值槽图片
	 */
	public static  Image RECT_EXP;
	
	/**
	 * 阴影图片
	 */
	public static  Image SHADOW;
	
	/**
	 * 控键设置图
	 */
	public static  Image PSP;
	
	/**
	 * 暂停图
	 */
	public static  Image PAUSE;
	
	/**
	 * 开始按钮
	 */
	public static  ImageIcon START_BTN;
	
	/**
	 * 设置按钮
	 */
	public static  ImageIcon SETTING_BTN;
	
	/**
	 * 提示方块的图片数组
	 */
	public static  Image[] NEXT_RECT;
	
	/**
	 * 背景图片
	 */
	public static  List<Image> BG_IMG;
	
	/**
	 * 图片路径
	 */
	public static final String GRAPHICS_PATH="graphics/";
	
	/**
	 * 默认路径
	 */
	private static final String DEFAULT_PATH="default";
	
	/**
	 * 设置游戏皮肤
	 */
	public static void setGameSkin(String path){
		 //皮肤路径
		 String skinPath=GRAPHICS_PATH+path;
		
		 //层窗口图片
		 WINDOW=new ImageIcon(skinPath+"/window/window.png").getImage();
		 //数据库图片
		 DATA=new ImageIcon(skinPath+"/string/dataBase.png").getImage();
		 //本地记录图片
		 DISK=new ImageIcon(skinPath+"/string/disk.png").getImage();
		 //等级图片
		 RANK=new ImageIcon(skinPath+"/string/rank.png").getImage();
		 //方块图片
		 RECT=new ImageIcon(skinPath+"/rectangle/rect.png").getImage();
		 //数字
		 NUMBERS=new ImageIcon(skinPath+"/string/numbers.png").getImage();
		 //分数
		 SCORE=new ImageIcon(skinPath+"/string/score.png").getImage();
		 //消行图
		 RMLINE=new ImageIcon(skinPath+"/string/rmline.png").getImage();
		 //名称图
		 NAME=new ImageIcon(skinPath+"/string/name.png").getImage();
		 //矩形值槽图片
		 RECT_EXP=new ImageIcon(skinPath+"/window/exp.png").getImage();
		 //阴影图片
		 SHADOW=new ImageIcon(skinPath+"/rectangle/shadow.png").getImage();
		 //控键设置图
		 PSP=new ImageIcon("data/psp.png").getImage();
		 //暂停图
		 PAUSE=new ImageIcon(skinPath+"/string/pause.png").getImage();
		 //开始按钮
		 START_BTN=new ImageIcon(skinPath+"/string/start.png");
		 //设置按钮
		 SETTING_BTN=new ImageIcon(skinPath+"/string/config.png");
		 
		//初始化方块图片数组
		NEXT_RECT=new Image[GameFactory.getRectNumber()];
		for(int i=0,length=NEXT_RECT.length;i<length;i++){
			NEXT_RECT[i]=new ImageIcon(skinPath+"/rectangle/"+i+".png").getImage();
		}
		//初始化背景图片集合
		BG_IMG=new ArrayList<Image>();
		//创建文件对象
		File file = new File(skinPath+"/background");
		//获取所有子文件
		File[] listFiles = file.listFiles();
		//遍历所有子文件
		for(File f:listFiles){
			//判断是否是文件夹
			if(!f.isDirectory()){
				//将图片文件添加到集合
				BG_IMG.add(new ImageIcon(f.getPath()).getImage());
			}
		}
	}
	
	static{
		
		setGameSkin(DEFAULT_PATH);
	}
}
