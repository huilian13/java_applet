package kevin.control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import kevin.config.DataInterfaceConfig;
import kevin.config.GameConfig;
import kevin.dao.Data;
import kevin.dto.GameDto;
import kevin.dto.Player;
import kevin.service.GameService;
import kevin.service.GameServiceImpl;
import kevin.window.GameFrame;
import kevin.window.GamePanel;
import kevin.window.GamePointsSaveFrame;
import kevin.window.GameSetting;

public class GameControl {
	/**
	 * 游戏数据源
	 */
	private GameService gameService;
	
	/**
	 * 游戏面板
	 */
	private GamePanel gamePanel;
	
	/**
	 * 玩家设置窗口
	 */
	private GameSetting gameSetting;
	
	/**
	 * 玩家记录保存窗口
	 */
	private GamePointsSaveFrame pointsSaveFrame;
	
	/**
	 * 数据访问接口A
	 */
	private Data dataBase;
	
	/**
	 * 数据访问接口B
	 */
	private Data dataDisk;
	
	/**
	 * 游戏数据源
	 */
	private GameDto gameDto;
	
	/**
	 * 游戏线程
	 */
	private Thread gameThread;
	
	/**
	 * 音乐线程
	 */
    public  Thread gameMusic;
	
	/**
	 * 游戏行为控制
	 */
	private HashMap<Integer,Method> actionList;

	public GameControl(){
		//创建游戏数据源对象
		this.gameDto=new GameDto();
		//初始化游戏业务逻辑对象
		this.gameService=new GameServiceImpl(gameDto);
		
		//初始化数据库访问接口
		this.dataBase=this.createDataInterface(GameConfig.getDataConfig().getDataBase());
		this.gameDto.setDbRecode(this.dataBase.loadData());
		//初始化本地磁盘数据访问接口
		this.dataDisk=this.createDataInterface(GameConfig.getDataConfig().getDataDisk());
		this.gameDto.setDiskRecode(this.dataDisk.loadData());
		
		//初始化游戏面板
		this.gamePanel=new GamePanel(gameDto,this);
		//初始化游戏控制
		this.setControlConfig();
		//初始化玩家设置窗口
		this.gameSetting=new GameSetting(this);
		//初始化玩家记录保存窗口
		this.pointsSaveFrame=new GamePointsSaveFrame(this);
	    //创建游戏窗口
	    new GameFrame(gamePanel);
	 
	}
	
	/**
	 * 初始化游戏控制
	 */
	@SuppressWarnings("unchecked")
	private void setControlConfig(){
		//初始化游戏控制（将键盘码映射到方法上）
		this.actionList=new HashMap<Integer, Method>();
		//对象输入流
		ObjectInputStream objIn=null;
		try {
			//初始化对象输入流
			objIn=new ObjectInputStream(new FileInputStream("data/control.dat"));
			//读取对象
			HashMap<Integer,String> actionsMap=(HashMap<Integer,String>)objIn.readObject();
			//将Map集合转换为Set集合
			Set<Entry<Integer, String>> entrySet = actionsMap.entrySet();
			for(Entry<Integer, String> entry:entrySet){
				//添加到集合
				this.actionList.put(entry.getKey(),this.gameService.getClass().getMethod(entry.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//关闭流
				objIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 创建数据对象 
	 * @return
	 */
	private Data createDataInterface(DataInterfaceConfig data){
		try {
			//获取类对象
			Class<?> clazz = Class.forName(data.getClassName());
			//获取类的构造器对象
			Constructor<?> constructor = clazz.getConstructor(HashMap.class);
			//创建对象
			return (Data)constructor.newInstance(data.getParamMap());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 游戏控制行为
	 * @param keyCode 按键值
	 */
	public void actionByKey(int keyCode) {
		try {
			//游戏开始时，按键才有效
			if(this.gameDto.isGameStart()){
				//判断是否有无效键
				if(this.actionList.containsKey(keyCode)){
					//创建游戏行为
					this.actionList.get(keyCode).invoke(this.gameService);
				}
			}
			//刷新游戏界面
			this.gamePanel.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示玩家设置窗口
	 */
	public void showPlayerConfig() {
		//显示设置窗口
		this.gameSetting.setVisible(true);
	}
	
	/**
	 * 设置皮肤后刷新画面
	 */
	public void repaint(){
		this.gamePanel.repaint();
	}
	
	/**
	 * 设置子窗口关闭时，刷新主窗口游戏
	 */
	public void setOver(){
		//关闭时刷新界面
		this.gamePanel.repaint();
		//返回焦点,实现控制监听
		this.gamePanel.requestFocus();
		//重新设置控制信息
		this.setControlConfig();
	}

	/**
	 * 开启游戏
	 */
	public void startGame() {
		//当游戏开始时，设置“开始”按钮无法点击
		this.gamePanel.buttonSwitch(false);
		//关闭窗口
		this.pointsSaveFrame.setVisible(false);
		this.gameSetting.setVisible(false);
		//启动游戏
		this.gameService.startGame();
		//刷新游戏
		this.gamePanel.repaint();
		//创建游戏线程
		this.gameThread=new GameThread();
		//开启游戏线程
		this.gameThread.start();
	}
	
	/**
	 * 游戏失败后的处理
	 */
	private void afterLose(){
		//显示记录保存窗口
		this.pointsSaveFrame.showWindow(this.gameDto.getNewScore());;
		//使按钮可以点击
		this.gamePanel.buttonSwitch(true);
	}
	
	/**
	 * 保存分数到本地磁盘
	 */
	public void savePointToDisk(String name) {
		//保存信息
		this.dataDisk.saveData(new Player(name, this.gameDto.getNewScore()));
		//加载信息到游戏窗口
		this.gameDto.setDiskRecode(this.dataDisk.loadData());
		//刷新游戏窗口
		this.gamePanel.repaint();
	}
	
	/**
	 * 保存分数到数据库
	 * @param name
	 */
	public void savePointToDB(String name) {
		//保存信息
		this.dataBase.saveData(new Player(name, this.gameDto.getNewScore()));
		//加载信息到游戏窗口
		this.gameDto.setDbRecode(this.dataBase.loadData());
		//刷新游戏窗口
		this.gamePanel.repaint();
		
	}

	/**
	 * 方块下落线程
	 * @author kevin
	 *
	 */
	private class GameThread extends Thread{
		@Override
		public void run() {
			try {
				//游戏开始时
				while(gameDto.isGameStart()){
					//线程睡眠
					Thread.sleep(gameDto.getSpeedTime());
					if(!gameDto.isPause()){
						//方块下落
						gameService.keyDown();
						//刷新游戏画面
						gamePanel.repaint();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//如果游戏结束，则计算得分
			afterLose();
		}
	}
	
	/**
	 * 游戏背景音乐线程
	 * @author kevin
	 *
	 */
/*	private class GameMusic extends Thread{
		@Override
		public void run() {
				try {
					for(;;){
						if(!gameDto.isGameStart()){
							this.interrupt();
							this.join();
							break;
						}
						//如果游戏结束,退出音乐线程
						MusicUtil.audioMp3("music/TetrisBgm.mp3");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
	}*/

}
