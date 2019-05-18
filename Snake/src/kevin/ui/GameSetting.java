package kevin.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import kevin.config.GameConfig;
import kevin.control.GameControl;
import kevin.util.FrameUtil;

/**
 * 游戏设置窗口
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class GameSetting extends JFrame{
	/**
	 * 游戏控制器
	 */
	private GameControl gameControl;

	/**
	 * 确定按钮
	 */
	private JButton btnOK=new JButton("确定");
	
	/**
	 * 应用按钮
	 */
	private JButton btnApply=new JButton("应用");
	
	/**
	 * 取消按钮
	 */
	private JButton btnCancel=new JButton("取消");
	
	/**
	 * “难度设置”标签
	 */
	private JLabel rankLabel=new JLabel("难度设置:");
	
	/**
	 * “方向键设置”标签
	 */
	private JLabel controLabel=new JLabel("方向键设置:");
	
	/**
	 * 错误提示标签
	 */
	private JLabel errorMessage=new JLabel();
	
	/**
	 * 标签对象
	 */
	private TextControl[] textControls=new TextControl[5];
	
	/**
	 * 方法名
	 */
	private static final String[] METHOD_NAME={"keyUp","keyDown","keyLeft","keyRight","keyFun"};
	
	/**
	 * 方向标记
	 */
	private static final String[] KEY_NAME={"↑","↓","←","→","暂停"};
	
	/**
	 * 文件路径
	 */
	private static final String PATH="data/control.dat";
	
	public GameSetting(GameControl gameControl){
		//初始化数据源
		this.gameControl=gameControl;
		//设置标题
		this.setTitle("游戏设置");
		//设置边界布局
		this.setLayout(new BorderLayout());
		//初始化输入框
		this.initKeyText();
		//添加难度设置面板
		this.add(this.createRankPanel(),BorderLayout.NORTH);
		//添加主面板
		this.add(this.createMainPanel(),BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.createButtonPanle(),BorderLayout.SOUTH);
		//设置窗口位置大小
		FrameUtil.frameCenter(356,306,this);
		//添加窗口事件监听
		this.addWindowActions();
	}
	
	/**
	 * 添加窗口事件监听
	 */
	private void addWindowActions(){
		//添加监听器
		this.addWindowListener(new WindowAdapter() {
			/**
			 * 窗口关闭时触发
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				//给主窗口返回焦点
				gameControl.configOver();
			}
			
			/**
			 * 窗口图标化时触发
			 */
			@Override
			public void windowIconified(WindowEvent e) {
				//给主窗口返回焦点
				gameControl.configOver();
			}
			
		});
	}
	
	/**
	 * 初始化按键输入框
	 */
	private void initKeyText(){
		int x=120;
		int y=32;
		int width=64;
		int height=20;
		for(int i=0;i<this.textControls.length;i++){
			this.textControls[i]=new TextControl(x,y,width,height,METHOD_NAME[i]);
			y+=32;
		}
		
		//声明对象流
		ObjectInputStream objectIn=null;
		try {
			//创建对象输入流
			objectIn=new ObjectInputStream(new FileInputStream(PATH));
			//读取对象
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> keyMap= (HashMap<Integer, String>)objectIn.readObject();
			//转换成Set集合
			Set<Entry<Integer,String>> entrySet = keyMap.entrySet();
			//遍历
			for(Entry<Integer,String> e:entrySet){
				for(TextControl text:this.textControls){
					if(text.getMethodName().equals(e.getValue())){
						text.setKeyCode(e.getKey());
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				objectIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 创建难度设置面板
	 * @return
	 */
	private JPanel createRankPanel() {
		//创建面板
		JPanel rankPanel=new JPanel();
		//设置背景颜色
		rankPanel.setBackground(Color.cyan);
		//设置流式布局(左对齐)
		rankPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//创建单选按钮
		JRadioButton radioButton1=new JRadioButton("简单",true);
		JRadioButton radioButton2=new JRadioButton("一般");
		JRadioButton radioButton3=new JRadioButton("困难");
		//创建ButtonGroup对象实现互斥
		ButtonGroup group=new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton3);
		//添加事件监听器
		radioButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//设置简单模式的速度
				gameControl.setSpeed(GameConfig.getSystemConfig().getSlowSpeed());
				
			}
		});
		radioButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//设置一般模式的速度
				gameControl.setSpeed(GameConfig.getSystemConfig().getNormalSpeed());
				
			}
		});
		
		radioButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 设置困难模式的速度
				gameControl.setSpeed(GameConfig.getSystemConfig().getFastSpeed());
				
			}
		});
		//添加到面板
		rankPanel.add(rankLabel);
		rankPanel.add(radioButton1);
		rankPanel.add(radioButton2);
		rankPanel.add(radioButton3);
		return rankPanel;
	}
	
	/**
	 * 创建按钮面板
	 * @return 面板对象
	 */
	private JPanel createButtonPanle() {
		//创建按钮面板
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//设置背景颜色
		buttonPanel.setBackground(Color.cyan);
		//设置提示标签颜色
		this.errorMessage.setForeground(Color.red);
		//添加提示标签
		buttonPanel.add(errorMessage);
		//添加“确定”按钮
		buttonPanel.add(btnOK);
		//添加“应用”按钮
		buttonPanel.add(btnApply);
		//添加“取消”按钮
		buttonPanel.add(btnCancel);
		
		//添加事件监听
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//写入配置文件
				if(writeConfig()){
					//关闭窗口
					setVisible(false);
					//关闭子窗口，刷新游戏
					gameControl.configOver();
				}
			}
		});
		
		//添加事件监听
		btnApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//写入配置文件
				writeConfig();
				
			}
		});
		
		//添加事件监听
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//关闭子窗口，刷新游戏
				gameControl.configOver();
				
			}
		});
		return buttonPanel;
	}

	/**
	 * 创建主面板
	 * @return 面板对象
	 */
	private JPanel createMainPanel() {
		//创建主面板
		JPanel mainPanel=new JPanel();
		//设置自由布局
		mainPanel.setLayout(null);
		//设置背景颜色
		mainPanel.setBackground(Color.cyan);
		//添加标签
		this.controLabel.setBounds(0, 0,100,20);
		mainPanel.add(this.controLabel);
		//在面板上显示输入框
		for(int i=0;i<this.textControls.length;i++){
			//获取输入框对象
			TextControl text = this.textControls[i];
			//创建标签对象
			JLabel label=new JLabel(KEY_NAME[i]);
			//设置位置
			label.setBounds(text.getX()-30,text.getY(),30,20);
			//添加到面板
			mainPanel.add(text);
			mainPanel.add(label);
		}
		return mainPanel;
	}
	
	/**
	 * 写入配置文件 
	 */
	protected boolean writeConfig() {
		//创建键值对集合
		HashMap<Integer,String> keyMap=new HashMap<Integer, String>();
		for(int i=0,length=this.textControls.length;i<length;i++){
			//获取单个输入框
			TextControl text=this.textControls[i];
			if(text.getKeyCode()==0){
				this.errorMessage.setText("错误按键");
				return false;
			}
			keyMap.put(text.getKeyCode(),text.getMethodName());
		}
		if(keyMap.size()!=5){
			this.errorMessage.setText("按键重复");
			return false;
		}
		try {
			//创建输出流
			ObjectOutputStream objectOut=new ObjectOutputStream(new FileOutputStream(PATH));
			//写入文件
			objectOut.writeObject(keyMap);
			//关闭资源 
			objectOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//清除消息
		this.errorMessage.setText(null);
		return true;
	}
}
