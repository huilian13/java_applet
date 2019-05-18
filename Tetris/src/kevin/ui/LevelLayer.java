package kevin.ui;

import java.awt.Graphics;
/**
 * 游戏等级层
 * @author kevin
 *
 */
public class LevelLayer extends Layer{
	
	/**
	 * 数字的x坐标
	 */
	private final int NUM_X;
	
	/**
	 * 数字的y坐标
	 */
	private final int NUM_Y;

	public LevelLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		//初始化数字的x坐标
		NUM_X=64;
		//初始化数字的y坐标
		NUM_Y=GameImage.RANK.getHeight(null)+(PADDING<<1);
				
		
	}

	@Override
	public void paint(Graphics g) {
		//绘制层窗口
		this.createWindow(g);
		//绘制字体图片
		g.drawImage(GameImage.RANK, this.x+PADDING, this.y+PADDING,null);
		//绘制数字
		this.drawNumberLeftPad(NUM_X,NUM_Y,this.gameDto.getRank(),3, g);
	}
	
    
}
