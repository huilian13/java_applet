package kevin.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kevin.control.GameControl;
import kevin.util.FrameUtil;

/**
 * 游戏结束窗口
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GameFinishFrame extends JFrame{
	
	/**
	 * 游戏控制器
	 */
	private GameControl gameControl;
	
	JButton nextButton= new JButton("继续下一关");
	
	JButton exitBtn=new JButton("结束游戏");
	
	/**
	 * 游戏关数
	 */
	private int index=0;
	
	public GameFinishFrame(GameControl gameControl){
		//初始化游戏控制器
		this.gameControl=gameControl;
		//设置标题
		this.setTitle("游戏完成");
		//添加游戏完成的面板
		this.add(this.createFinishPanel(),BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.createButtonPanel(),BorderLayout.SOUTH);
		//设置窗口大小并居中
		FrameUtil.frameCenter(300,150, this);
	}

	/**
	 * 创建按钮面板
	 * @return 面板对象
	 */
	private JPanel createButtonPanel() {
		// 创建面板
		final JPanel buttonpPanel=new JPanel();
		//添加事件监听
		this.nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(index>=4){
					nextButton.setText("您已通关");
					//刷新画面
					buttonpPanel.repaint();
					return;
				}
				// 启动下一关的游戏画面
				gameControl.startNextGame(++index);
				
			}
		});
		//添加事件监听
		this.exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//结束游戏
				gameControl.setOver();
				
			}
		});
		//添加按钮
		buttonpPanel.add(this.nextButton);
		
		buttonpPanel.add(this.exitBtn);
		return buttonpPanel;
	}

	/**
	 * 创建提示游戏完成面板
	 * @return 面板对象
	 */
	private JPanel createFinishPanel() {
		//创建面板
		JPanel panel=new JPanel(){
			@Override
			public void paint(Graphics g) {
				//居中
				Image overImg=GameImage.OVER;
				int x=this.getWidth()-overImg.getWidth(null)>>1;
				int y=this.getHeight()-overImg.getHeight(null)>>1;
				//绘制游戏完成提示
				g.drawImage(overImg,x,y, null);
			}
		};
	      return panel;
	}

}
