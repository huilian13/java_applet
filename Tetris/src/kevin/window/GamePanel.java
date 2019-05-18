package kevin.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import kevin.config.GameConfig;
import kevin.config.LayerConfig;
import kevin.control.GameControl;
import kevin.control.PlayerControl;
import kevin.dto.GameDto;
import kevin.ui.GameImage;
import kevin.ui.Layer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	/**
	 * 按钮的宽
	 */
	private static final int BUTTON_W=GameConfig.getFrameConfig().getButtonConfig().getWidth();
	
	/**
	 * 按钮的高
	 */
	private static final int BUTTON_H=GameConfig.getFrameConfig().getButtonConfig().getHeight();

	/**
	 * 层窗口集合
	 */
	private List<Layer> layers=null;
	
	/**
	 * 游戏控制对象
	 */
	private GameControl gameControl;
	
	/**
	 * 开始按钮
	 */
	private JButton startButton;
	
	/**
	 * 设置按钮
	 */
	private JButton settingButton;
	
	public GamePanel(GameDto gameDto,GameControl gameControl){
		//设置布局管理器为自由布局
		this.setLayout(null);
		//初始化游戏控制对象
		this.gameControl=gameControl;
		//初始化层窗口
		this.initLayer(gameDto);
		//初始化动作组件
		this.initAction();
		//初始化按钮
		this.initButton();
	}
	
	/**
	 * 初始化按钮
	 */
	private void initButton(){
		//初始化开始按钮
		this.startButton=new JButton(GameImage.START_BTN);
		//设置开始按钮的位置
		this.startButton.setBounds(GameConfig.getFrameConfig().getButtonConfig().getStartX(),
				                   GameConfig.getFrameConfig().getButtonConfig().getStartY(), 
				                   BUTTON_W,BUTTON_H);
		//添加到面板
		this.add(this.startButton);
		//初始化设置按钮
		this.settingButton=new JButton(GameImage.SETTING_BTN);
		//设置按钮位置
		this.settingButton.setBounds(GameConfig.getFrameConfig().getButtonConfig().getSettingX(),
				                     GameConfig.getFrameConfig().getButtonConfig().getSettingY(), 
				                     BUTTON_W,BUTTON_H);
		//添加到面板
		this.add(settingButton);
		
		//添加“开始按钮”的事件监听
		this.startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 启动游戏
				gameControl.startGame();
				requestFocus();
			}
		});
		
		//添加“设置按钮”的事件监听
		this.settingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//显示设置框
				gameControl.showPlayerConfig();
			}
		});
	}
	
	/**
	 * 初始化动作组件
	 */
	private void initAction(){
		//添加事件监听
		this.addKeyListener(new PlayerControl(this.gameControl));
	}
	
	/**
	 * 控制按钮是否可以点击
	 * @param off
	 */
    public void buttonSwitch(boolean off){
		this.startButton.setEnabled(off);
	}
    
	/**
	 * 初始化层窗口
	 */
	private void initLayer(GameDto gameDto){
		try {
			//获取所有层窗口配置对象
			List<LayerConfig> layerConfigs = GameConfig.getFrameConfig().getLayerConfigs();
			//初始化层窗口集合
			this.layers=new ArrayList<Layer>(layerConfigs.size());
			//声明层窗口配置对象
			LayerConfig layerConfig=null;
			//声明层对象
			Layer layer=null;
			//遍历所有层窗口对象
			for(int i=0,length=layerConfigs.size();i<length;i++){
				//获取单个层窗口配置对象
				layerConfig = layerConfigs.get(i);
				//获取类的字节码
			    Class<?> clazz = Class.forName(layerConfig.getClassName());	
			    //获取类的构造函数
			    Constructor<?> constructor = clazz.getConstructor(int.class,int.class,int.class,int.class);
			    //构造对象
			    layer=(Layer)constructor.newInstance(layerConfig.getX(),layerConfig.getY(),layerConfig.getWidth(),layerConfig.getHeight());
			    //在层对象中设置游戏数据源
			    layer.setGameDto(gameDto);
			    //将对象添加到集合
			    this.layers.add(layer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//调用基类方法
		super.paintComponent(g);
		//循环刷新游戏画面
    	for(int i=0,length=this.layers.size();i<length;this.layers.get(i++).paint(g));
    	
	}
}
