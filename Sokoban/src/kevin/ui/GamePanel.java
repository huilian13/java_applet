package kevin.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import kevin.config.GameConfig;
import kevin.control.GameControl;
import kevin.control.PlayerControl;
import kevin.dto.GameDto;
import kevin.entity.Coop;
import kevin.entity.SheepBox;
/**
 * 游戏面板
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	/**
	 * 游戏数据源
	 */
	private GameDto gameDto;
	
	/**
	 * 图片尺寸
	 */
	private final int SIZE=GameConfig.getFrameConfig().getSize();
	
	public GamePanel(GameDto gameDto,GameControl gameControl) {
		//初始化游戏数据源
		this.gameDto=gameDto;
		//注册键盘事件监听器
		this.addKeyListener(new PlayerControl(gameControl));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//绘制游戏背景
		g.drawImage(GameImage.BG_IMG,0,0,null);
		//绘制人物(灰太狼)
		g.drawImage(this.gameDto.getWolfImage(),this.gameDto.getWolf().getX()*SIZE,this.gameDto.getWolf().getY()*SIZE,null);
		//绘制箱子（羊）
		List<SheepBox> boxList = this.gameDto.getBoxList();
		for(SheepBox box:boxList){
			g.drawImage(GameImage.BOX,box.getX()*SIZE,box.getY()*SIZE,null);
		}
		//绘制障碍物（树）
		int[][] gameMap = this.gameDto.getGameMap();
		for(int i=0;i<gameMap.length;i++){
			for(int j=0;j<gameMap[i].length;j++){
				if(gameMap[i][j]==1){
					g.drawImage(GameImage.BLOCK,j*SIZE,i*SIZE,null);
				}
			}
		}
		//绘制笼子
		List<Coop> coopList = this.gameDto.getCoopList();
		for(Coop coop:coopList){
			g.drawImage(GameImage.TARGET,coop.getX()*SIZE,coop.getY()*SIZE,null);
		}
		//添加关闭按钮
		this.addButton(new JButton(GameImage.BTN_IMG));
		//返回控制焦点
		this.requestFocus();
	}
	
	/**
	 * 添加鼠标按钮
	 */
	private final void addButton(JButton button){
		//设置鼠标背景颜色
		button.setBackground(Color.white);
		//添加鼠标事件
		button.addMouseListener(new ButtonAdapter());
		//设置按钮位置
		button.setBounds(750,0,60,20);
		//将按钮添加到面板
		this.add(button);
		
	}
}
