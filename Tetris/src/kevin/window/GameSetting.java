package kevin.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import kevin.control.GameControl;
import kevin.ui.GameImage;
import kevin.utils.FrameUtil;

@SuppressWarnings("serial")
public class GameSetting extends JFrame{
	
	/**
	 * 游戏控制器
	 */
	private GameControl gameControl ;

	/**
	 * 确定按钮
	 */
	private JButton btnOK=new JButton("确定");
	
	/**
	 * 取消按钮
	 */
	private JButton btnCancel=new JButton("取消");
	
	/**
	 * 应用按钮
	 */
	private JButton btnApply=new JButton("应用");
	
	/**
	 * 错误提示信息
	 */
	private JLabel errorMessage=new JLabel();
	
	/**
	 * 按键输入框数组
	 */
	private TextControl[] textControls=new TextControl[8];
	
	/**
	 * 按键名数组
	 */
	private JLabel[] textLabel;
	
	/**
	 * 列表模式
	 */
	private DefaultListModel<String> skinData;
	
	/**
	 * 列表
	 */
	private  JList<?> skinList;
	
	/**
	 * 预览图
	 */
	private Image[] skinViewList;
	
	/**
	 * 方法名数组
	 */
	private static final String[] METHOD_NAMES=new String[]{
			"keyRight","keyUp","keyLeft","keyDown",
			"keyFunLeft","keyFunUp","keyFunRight","keyFunDown",
	};
	
	public GameSetting(GameControl gameControl){
		//设置题目
		this.setTitle("游戏设置");
		//设置边界布局管理器
		this.setLayout(new BorderLayout());
		//初始化游戏控制器
		this.gameControl=gameControl;
		//初始化按键名
		this.initTextLabel();
		//初始化按键输入框
		this.initKeyText();
		//添加主面板
		this.add(this.createMainPanel(),BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.creatButtonPanel(),BorderLayout.SOUTH);
		//创建窗口
		FrameUtil.frameCenter(680,365,this);
		//添加窗口事件监听
		this.addWindowEvent();
	}
	
	/**
	 * 添加窗口事件
	 */
	private void addWindowEvent(){
		//添加事件监听
		this.addWindowListener(new WindowAdapter() {
			/**
			 * 窗口正在关闭时触发
			 */
		    @Override
			public void windowClosing(WindowEvent e) {
				// 窗口关闭时，刷新主窗口游戏
		    	gameControl.setOver();
			}
		   
		    /**
		     * 窗口图标化时触发
		     */
		    @Override
		    public void windowIconified(WindowEvent e) {
		    	// 窗口关闭时，刷新主窗口游戏
		    	gameControl.setOver();
		    }
			
		});
	}
	/**
	 * 初始化按键名称
	 */
	private void initTextLabel(){
		this.textLabel=new JLabel[]{
          new JLabel("→"),new JLabel("↑"),new JLabel("←"),new JLabel("↓"),
          new JLabel("阴影"),new JLabel("开挂"),new JLabel("暂停"),new JLabel("瞬移")
		};
	}
	

	/**
	 * 初始化按键输入框
	 */
	private void initKeyText() {
		int x=20;
		int y=52;
		int width=64;
		int height=20;
		for(int i=0;i<4;i++){
			this.textControls[i]=new TextControl(x, y, width,height,METHOD_NAMES[i]);
			y+=32;
		}
		x=570;
		y=52;
		for(int i=4;i<8;i++){
			this.textControls[i]=new TextControl(x, y, width,height,METHOD_NAMES[i]);
			y+=32;
		}
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("data/control.dat"));
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> keysMap= (HashMap<Integer,String>)in.readObject();
			in.close();
			Set<Entry<Integer,String>> entrySet = keysMap.entrySet();
			for(Entry<Integer,String> entry:entrySet){
				for(TextControl text:this.textControls){
					if(text.getMethodName().equals(entry.getValue())){
						text.setKeyCode(entry.getKey());
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建按钮面板
	 * @return 面板对象
	 */                                                                                                  
	private JPanel creatButtonPanel() {
		//创建面板
		JPanel panel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//设置错误信息标签
		this.errorMessage.setForeground(Color.red);
        panel.add(this.errorMessage);
        //添加确定按钮
        panel.add(this.btnOK);
        //添加取消按钮
        panel.add(this.btnCancel);
        //添加应用按钮
        panel.add(this.btnApply);
        
		//添加“确定按钮”事件监听
		this.btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//写入按钮配置信息
				if(writeConfig()){
					//关闭窗口
					setVisible(false);
					//关闭窗口后刷新游戏
					gameControl.setOver();
				}
			}
		});
		//添加“取消按钮”事件监听
		this.btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//关闭窗口
				setVisible(false);
				//关闭窗口后刷新游戏
				gameControl.setOver();
				
			}
		});
		//添加“应用按钮”事件监听
		this.btnApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 写入配置
				writeConfig();
				//换皮肤后刷新画面
				gameControl.repaint();
				
			}
		});
		//关闭窗口后刷新游戏
		return panel;
	}

	/**
	 * 创建主面板
	 * @return 选项卡面板对象
	 */
	private JTabbedPane createMainPanel() {
		//创建选项卡面板
		JTabbedPane tabbedPane=new JTabbedPane();
		tabbedPane.addTab("控制设置",this.createControlPanel());
		tabbedPane.addTab("皮肤设置",this.creatSkinPanel());
		
		return tabbedPane;
	}
	
	/**
	 * 创建皮肤设置面板
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Component creatSkinPanel() {
		//创建面板
		final JPanel panel=new JPanel(new BorderLayout());
		//创建列表模型
		this.skinData=new DefaultListModel<String>();
		//创建图片文件对象
		File dir=new File(GameImage.GRAPHICS_PATH);
		//获取图片文件下的所有文件
		File[] files = dir.listFiles();
		//初始化预览图数组
		this.skinViewList=new Image[files.length];
		for(int i=0,length=files.length;i<length;i++){
			//将文件名添加到列表
			skinData.addElement(files[i].getName());
			//添加预览图
			this.skinViewList[i]=new ImageIcon(files[i].getPath()+"/view.png").getImage();
			
		}
        //创建列表
		this.skinList=new JList(this.skinData);
        //给列表添加鼠标监听
        skinList.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//刷新画面
        		panel.repaint();
        	}
		});
        //设置默认预览图
        skinList.setSelectedIndex(0);
        //皮肤预览面板
        JPanel skinViewPanel=new JPanel(){
        	@Override
        	public void paint(Graphics g) {
        		//获取预览图
        		Image viewImage= skinViewList[skinList.getSelectedIndex()];
        		//居中坐标
        		int x=this.getWidth()-viewImage.getWidth(null)>>1;
        		int y=this.getHeight()-viewImage.getHeight(null)>>1;
        		//绘制预览图
        		g.drawImage(viewImage,x,y,null);
        	}
        };
        //添加列表到面板
        panel.add(new JScrollPane(skinList),BorderLayout.WEST);
        //添加预览面板到面板
        panel.add(skinViewPanel,BorderLayout.CENTER);
		return panel;
	}

	/**
	 * 创建控制设置面板
	 * @return 面板对象
	 */
	private Component createControlPanel() {
		//创建面板
		JPanel panel=new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(GameImage.PSP,0,0,null);
			}
		};
		//设置布局管理器
		panel.setLayout(null);
		//声明文本组件
		TextControl text=null;
		//声明标签组件
		JLabel label=null;
		for(int i=0;i<textControls.length;i++){
			//获取文本
			text=textControls[i];
			//获取标签
			label=textLabel[i];
			//设置标签位置
			if(i<4){
				label.setBounds(text.getX()-20,text.getY(),20,text.getHeight());
			}else{
				label.setBounds(text.getX()+text.getWidth(),text.getY(),30,text.getHeight());
			}
			//添加组件
			panel.add(label);
			panel.add(text);
		}
		return panel;
	}
	
	/**
	 * 写入按钮配置信息
	 */
	private boolean writeConfig(){
		//创建键值对集合
		HashMap<Integer,String> keySet=new HashMap<Integer, String>();
		for(int i=0,length=this.textControls.length;i<length;i++){
			int keyCode=this.textControls[i].getKeyCode();
			//未知按键
			if(keyCode==0){
				this.errorMessage.setText("错误按钮");
				return false;
			}
			//添加到集合
			keySet.put(keyCode,this.textControls[i].getMethodName());
		}
		//按键重复
		if(keySet.size()!=8){
			this.errorMessage.setText("按钮重复");
			return false;
		}
		try {
			//获取皮肤路径
			String skinPath= this.skinData.get(this.skinList.getSelectedIndex());
			//设置皮肤
			GameImage.setGameSkin(skinPath);
			//创建对象输出流
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("data/control.dat"));
			//向文件写入对象
			out.writeObject(keySet);
			//关闭流
			out.close();
		} catch (Exception e) {
			this.errorMessage.setText(e.getMessage());
			return false;
		} 
		//写入成功，则去除提示信息
		this.errorMessage.setText(null);
		return true;
	}

}
