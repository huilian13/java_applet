package kevin.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * 游戏图片
 * @author kevin
 *
 */
public class GameImage {

	/**
	 * 游戏背景图
	 */
	public static Image BG_IMG;
	
	
	/**
	 * 游戏图标
	 */
	public static Image TITLE;
	
	/**
	 * 人物正面图
	 */
	public static Image WOLF_B;
	
	/**
	 * 人物背面图
	 */
	public static Image WOLF_A;
	
	/**
	 * 人物左面图
	 */
	public static Image WOLF_L;
	
	/**
	 * 人物右面图
	 */
	public static Image WOLF_R;
	
	/**
	 * 箱子图
	 */
	public static Image BOX;
	
	/**
	 * 笼子
	 */
	public static Image TARGET;
	
	/**
	 * 障碍物（树）
	 */
	public static Image BLOCK;
	
	/**
	 * 游戏结束标志
	 */
	public static Image OVER;
	
	/**
	 * 按钮
	 */
	public static ImageIcon BTN_IMG;
	
	/**
	 * 图片路径
	 */
	public static final String GRAPHICS_PATH="graphics/";
	
	/**
	 * 设置游戏皮肤
	 * @param path 皮肤路径
	 */
	public static void setGameSkin(String path){
		//皮肤路径
		String skinPath=GRAPHICS_PATH+path;
		//游戏背景图
		BG_IMG=new ImageIcon(skinPath+"/ground.png").getImage();
		//障碍物（树）
		BLOCK=new ImageIcon(skinPath+"/block.png").getImage();
		//游戏结束标志
		OVER=new ImageIcon(GRAPHICS_PATH+"/victory.png").getImage();
		//游戏图标
		TITLE=new ImageIcon(GRAPHICS_PATH+"/title.jpg").getImage();
		//人物正面图
		WOLF_B=new ImageIcon(GRAPHICS_PATH+"/wolf_B.png").getImage();
		//人物背面图
		WOLF_A=new ImageIcon(GRAPHICS_PATH+"/wolf_A.png").getImage();
		//人物左面图
		WOLF_L=new ImageIcon(GRAPHICS_PATH+"/wolf_L.png").getImage();
		//人物右面图
		WOLF_R=new ImageIcon(GRAPHICS_PATH+"/wolf_R.png").getImage();
		//箱子图
		BOX=new ImageIcon(GRAPHICS_PATH+"/sheep.png").getImage();
		//笼子
		TARGET=new ImageIcon(GRAPHICS_PATH+"/target.png").getImage();
		//关闭按钮
		BTN_IMG=new ImageIcon(GRAPHICS_PATH+"/exit.png");
	}
	
	static{
		//初始化游戏皮肤
		setGameSkin("newSkin1");
	}
}
