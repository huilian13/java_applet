	package kevin.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

import kevin.config.GameConfig;
import kevin.control.GameControl;
import kevin.control.PlayerControl;
import kevin.dto.GameDto;
import kevin.entity.Food;
import kevin.factory.GameFactory;

/**
 * 游戏面板类
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	/**
	 * 方块尺寸
	 */
	private static final int SIZE=GameConfig.getFrameConfig().getSize();
	
	/**
	 * 按钮的宽度
	 */
	private static final int BUTTON_W=GameFactory.getButtonConfig().getWidth();
	
	/**
	 * 按钮的高度
	 */
	private static final int BUTTON_H=GameFactory.getButtonConfig().getHeight();
	
	/**
	 * 开始按钮
	 */
	private JButton startButton;
	
	/**
	 * 配置按钮
	 */
	private JButton configButton;
	
	/**
	 * 游戏数据源
	 */
	private GameDto gameDto;
	
	/**
	 * 游戏控制器
	 */
	private GameControl gameControl;

	public GamePanel(GameDto gameDto,GameControl gameControl) {
		//设置自由布局(面板默认是流式布局)
		this.setLayout(null);
		//初始化数据源
		this.gameDto=gameDto;
		//初始化游戏控制器
		this.gameControl=gameControl;
		//添加键盘监听
		this.addKeyListener(new PlayerControl(gameControl));
		//初始化按钮
		this.initButton();
	}
	
	/**
	 * 初始化按钮
	 */
	private void initButton(){
		//初始化开始按钮
		this.startButton=new JButton(GameImage.START_BUTTON);
		//设置开始按钮位置
		this.startButton.setBounds(GameFactory.getButtonConfig().getStartX(),
				                   GameFactory.getButtonConfig().getStartY(),
				                   BUTTON_W,BUTTON_H);
		//添加到面板
		this.add(startButton);
		
		//添加事件监听
		this.startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//开始游戏
				gameControl.startGame();
				//返回焦点
				requestFocus();
			}
		});
		
		//初始化设置按钮
		this.configButton=new JButton(GameImage.CONFIG_BUTTON);
		//设置设置按钮的位置
		this.configButton.setBounds(GameFactory.getButtonConfig().getConfigX(),
				                    GameFactory.getButtonConfig().getConfigY(),
				                    BUTTON_W,BUTTON_H);
		//添加到面板
		this.add(configButton);
		
		//添加事件监听
		this.configButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//显示游戏设置窗口
				gameControl.showPlayerConfig();
			}
		});
	}
	
	/**
	 * 按钮选择
	 * @param off true代表能点击
	 */
	public void buttonSwitch(boolean off) {
		this.startButton.setEnabled(off);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//调用基类方法
		super.paintComponent(g);
		//绘制背景
		this.drawBackGround(g);
		//绘制砖块(障碍物)
		this.drawRectBrick(g);
		//如果游戏开始，就绘制蛇和食物
		if(this.gameDto.getSnake()!=null){
			//绘制蛇
			this.drawSnake(g);
			//绘制食物
			this.drawFood(g);
			//游戏结束时，绘制字母
			if(!this.gameDto.isStart()){
				this.drawGameOver(g);
			}
		}
		//游戏暂停时绘制
		if(this.gameDto.isPause()){
			//获取图片
			Image pauseImage = GameImage.PAUSE;
			//计算坐标
			int x=this.getWidth()-pauseImage.getWidth(null)>>1;
			int y=this.getHeight()-pauseImage.getHeight(null)>>1;
			//绘制图片
			g.drawImage(pauseImage,x, y,null);
		}
		
		//绘制得分
		g.setColor(Color.gray);
		g.setFont(new Font("黑体",Font.BOLD,20));
		g.drawString("得分："+this.gameDto.getSorce(),600,640);
	}

	/**
	 * 绘制背景
	 * @param g 画笔对象
	 */
	private void drawBackGround(Graphics g){
		//绘制背景
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0,GameDto.MAP_ROW*SIZE,GameDto.MAP_COL*SIZE);
	}
	
	/**
	 * 绘制砖块
	 */
	private void drawRectBrick(Graphics g){
		//获取游戏地图
		boolean[][] gameMap = this.gameDto.getGameMap();
		//绘制砖块
		for(int x=0;x<gameMap.length;x++){
			for(int y=0;y<gameMap[x].length;y++){
				if(gameMap[x][y]){
					g.setColor(Color.gray);
					g.fill3DRect(x*SIZE,y*SIZE,SIZE,SIZE,true);
				}
			}
		}
	}
	
	/**
	 * 绘制蛇身
	 * @param g
	 */
	private void drawSnake(Graphics g) {
		//获取蛇节点
		LinkedList<Point> snakeList = this.gameDto.getSnake().getSnakeList();
		//声明蛇身节点对象
		Point body;
		//设置画笔颜色
		g.setColor(Color.yellow);
		//画蛇身
		for(int i=1;i<snakeList.size();i++){
			body= snakeList.get(i);
			g.fill3DRect(body.x*SIZE, body.y*SIZE,SIZE,SIZE,true);
		}
		//获取蛇头节点
		Point head= snakeList.getFirst();
		//画蛇头
		g.setColor(Color.cyan);
		g.fill3DRect(head.x*SIZE, head.y*SIZE,SIZE,SIZE,true);
		
	}
	
	/**
	 * 绘制食物
	 * @param g
	 */
	private void drawFood(Graphics g){
		//获取食物对象
		Food food = this.gameDto.getFood();
		//获取食物坐标
		Point point = food.getfoodPoint();
		//绘制食物
		g.setColor(Color.green);
		g.fill3DRect(point.x*SIZE, point.y*SIZE,SIZE,SIZE,true);
	}
	
	/**
	 * 绘制gameOver
	 * @param g
	 */
	private void drawGameOver(Graphics g){
		g.setColor(Color.gray);
		//设置字体格式
		g.setFont(new Font("黑体",Font.BOLD,100));
		//绘制字体
		g.drawString("GameOver",256,300);
	}
}
