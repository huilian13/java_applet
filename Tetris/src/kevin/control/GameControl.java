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
	 * ��Ϸ����Դ
	 */
	private GameService gameService;
	
	/**
	 * ��Ϸ���
	 */
	private GamePanel gamePanel;
	
	/**
	 * ������ô���
	 */
	private GameSetting gameSetting;
	
	/**
	 * ��Ҽ�¼���洰��
	 */
	private GamePointsSaveFrame pointsSaveFrame;
	
	/**
	 * ���ݷ��ʽӿ�A
	 */
	private Data dataBase;
	
	/**
	 * ���ݷ��ʽӿ�B
	 */
	private Data dataDisk;
	
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto gameDto;
	
	/**
	 * ��Ϸ�߳�
	 */
	private Thread gameThread;
	
	/**
	 * �����߳�
	 */
    public  Thread gameMusic;
	
	/**
	 * ��Ϸ��Ϊ����
	 */
	private HashMap<Integer,Method> actionList;

	public GameControl(){
		//������Ϸ����Դ����
		this.gameDto=new GameDto();
		//��ʼ����Ϸҵ���߼�����
		this.gameService=new GameServiceImpl(gameDto);
		
		//��ʼ�����ݿ���ʽӿ�
		this.dataBase=this.createDataInterface(GameConfig.getDataConfig().getDataBase());
		this.gameDto.setDbRecode(this.dataBase.loadData());
		//��ʼ�����ش������ݷ��ʽӿ�
		this.dataDisk=this.createDataInterface(GameConfig.getDataConfig().getDataDisk());
		this.gameDto.setDiskRecode(this.dataDisk.loadData());
		
		//��ʼ����Ϸ���
		this.gamePanel=new GamePanel(gameDto,this);
		//��ʼ����Ϸ����
		this.setControlConfig();
		//��ʼ��������ô���
		this.gameSetting=new GameSetting(this);
		//��ʼ����Ҽ�¼���洰��
		this.pointsSaveFrame=new GamePointsSaveFrame(this);
	    //������Ϸ����
	    new GameFrame(gamePanel);
	 
	}
	
	/**
	 * ��ʼ����Ϸ����
	 */
	@SuppressWarnings("unchecked")
	private void setControlConfig(){
		//��ʼ����Ϸ���ƣ���������ӳ�䵽�����ϣ�
		this.actionList=new HashMap<Integer, Method>();
		//����������
		ObjectInputStream objIn=null;
		try {
			//��ʼ������������
			objIn=new ObjectInputStream(new FileInputStream("data/control.dat"));
			//��ȡ����
			HashMap<Integer,String> actionsMap=(HashMap<Integer,String>)objIn.readObject();
			//��Map����ת��ΪSet����
			Set<Entry<Integer, String>> entrySet = actionsMap.entrySet();
			for(Entry<Integer, String> entry:entrySet){
				//��ӵ�����
				this.actionList.put(entry.getKey(),this.gameService.getClass().getMethod(entry.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//�ر���
				objIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �������ݶ��� 
	 * @return
	 */
	private Data createDataInterface(DataInterfaceConfig data){
		try {
			//��ȡ�����
			Class<?> clazz = Class.forName(data.getClassName());
			//��ȡ��Ĺ���������
			Constructor<?> constructor = clazz.getConstructor(HashMap.class);
			//��������
			return (Data)constructor.newInstance(data.getParamMap());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * ��Ϸ������Ϊ
	 * @param keyCode ����ֵ
	 */
	public void actionByKey(int keyCode) {
		try {
			//��Ϸ��ʼʱ����������Ч
			if(this.gameDto.isGameStart()){
				//�ж��Ƿ�����Ч��
				if(this.actionList.containsKey(keyCode)){
					//������Ϸ��Ϊ
					this.actionList.get(keyCode).invoke(this.gameService);
				}
			}
			//ˢ����Ϸ����
			this.gamePanel.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ʾ������ô���
	 */
	public void showPlayerConfig() {
		//��ʾ���ô���
		this.gameSetting.setVisible(true);
	}
	
	/**
	 * ����Ƥ����ˢ�»���
	 */
	public void repaint(){
		this.gamePanel.repaint();
	}
	
	/**
	 * �����Ӵ��ڹر�ʱ��ˢ����������Ϸ
	 */
	public void setOver(){
		//�ر�ʱˢ�½���
		this.gamePanel.repaint();
		//���ؽ���,ʵ�ֿ��Ƽ���
		this.gamePanel.requestFocus();
		//�������ÿ�����Ϣ
		this.setControlConfig();
	}

	/**
	 * ������Ϸ
	 */
	public void startGame() {
		//����Ϸ��ʼʱ�����á���ʼ����ť�޷����
		this.gamePanel.buttonSwitch(false);
		//�رմ���
		this.pointsSaveFrame.setVisible(false);
		this.gameSetting.setVisible(false);
		//������Ϸ
		this.gameService.startGame();
		//ˢ����Ϸ
		this.gamePanel.repaint();
		//������Ϸ�߳�
		this.gameThread=new GameThread();
		//������Ϸ�߳�
		this.gameThread.start();
	}
	
	/**
	 * ��Ϸʧ�ܺ�Ĵ���
	 */
	private void afterLose(){
		//��ʾ��¼���洰��
		this.pointsSaveFrame.showWindow(this.gameDto.getNewScore());;
		//ʹ��ť���Ե��
		this.gamePanel.buttonSwitch(true);
	}
	
	/**
	 * ������������ش���
	 */
	public void savePointToDisk(String name) {
		//������Ϣ
		this.dataDisk.saveData(new Player(name, this.gameDto.getNewScore()));
		//������Ϣ����Ϸ����
		this.gameDto.setDiskRecode(this.dataDisk.loadData());
		//ˢ����Ϸ����
		this.gamePanel.repaint();
	}
	
	/**
	 * ������������ݿ�
	 * @param name
	 */
	public void savePointToDB(String name) {
		//������Ϣ
		this.dataBase.saveData(new Player(name, this.gameDto.getNewScore()));
		//������Ϣ����Ϸ����
		this.gameDto.setDbRecode(this.dataBase.loadData());
		//ˢ����Ϸ����
		this.gamePanel.repaint();
		
	}

	/**
	 * ���������߳�
	 * @author kevin
	 *
	 */
	private class GameThread extends Thread{
		@Override
		public void run() {
			try {
				//��Ϸ��ʼʱ
				while(gameDto.isGameStart()){
					//�߳�˯��
					Thread.sleep(gameDto.getSpeedTime());
					if(!gameDto.isPause()){
						//��������
						gameService.keyDown();
						//ˢ����Ϸ����
						gamePanel.repaint();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//�����Ϸ�����������÷�
			afterLose();
		}
	}
	
	/**
	 * ��Ϸ���������߳�
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
						//�����Ϸ����,�˳������߳�
						MusicUtil.audioMp3("music/TetrisBgm.mp3");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
	}*/

}
