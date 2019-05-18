package kevin.ui;

import java.awt.Graphics;

import kevin.config.GameConfig;
/**
 * 游戏得分层窗口
 * @author kevin
 *
 */
public class ScoreLayer extends Layer{
	/**
	 * 数字显示的x坐标
	 */
	private final int COM_X;
	
	/**
	 * 起始x坐标
	 */
	private final int START_X;
	
	/**
	 * 分数标题的y坐标
	 */
	private final int SCORE_Y;
	
	/**
	 * 消行标题的y坐标
	 */
	private final int RMLINE_Y;
	
	/**
	 * 值槽的y坐标
	 */
	private final int EXP_Y;
	
	/**
	 * 数字的最大位数
	 */
	private static final int NUM_BIT=5;
	
	/**
	 * 最大消行数
	 */
	private static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();
	
	public ScoreLayer(int x, int y, int width, int height) {
		super(x, y, width, height);
		//初始化数字显示的x坐标
		COM_X=this.width-NUM_W*NUM_BIT-PADDING;
		//获取标题的高度
		int imgH=GameImage.SCORE.getHeight(null);
		//初始化起始的x坐标
		START_X=this.x+PADDING;
		//初始化话分数标题的y坐标
		SCORE_Y=PADDING;
		//初始化消行显示的y坐标
		RMLINE_Y=imgH+(PADDING<<1);
		//初始化值槽的y坐标
		EXP_Y=this.y+RMLINE_Y+imgH+PADDING;
		
	}

	@Override
	public void paint(Graphics g) {
		//绘制层窗口
		this.createWindow(g);
		//绘制标题和数字
		this.drawTitleAndNum(g);
		//获取消行数
		int rmline=this.gameDto.getNewRmline();
		//绘制值槽
		this.drawSlot(EXP_Y,"下一级",null,(double)(rmline%LEVEL_UP)/(double)LEVEL_UP,g);
	}
	
	/**
	 * 绘制标题和数字
	 * @param g 画笔对象
	 */
	private void drawTitleAndNum(Graphics g){
		//绘制窗口标题（分数）
		g.drawImage(GameImage.SCORE, START_X, this.y+SCORE_Y,null);
		//绘制分数
		this.drawNumberLeftPad(COM_X,SCORE_Y,this.gameDto.getNewScore(),NUM_BIT, g);
		//绘制窗口标题（消行）
		g.drawImage(GameImage.RMLINE,START_X, this.y+RMLINE_Y,null);
		//绘制消行数目
		this.drawNumberLeftPad(COM_X, RMLINE_Y,this.gameDto.getNewRmline(), NUM_BIT, g);
	}
	
}
