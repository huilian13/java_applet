package kevin.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import kevin.dto.GameDto;
import kevin.service.GameService;
import kevin.service.GameServiceImpl;
import kevin.ui.GameFrame;
import kevin.ui.GamePanel;
import kevin.ui.GameSetting;
/**
 * ��Ϸ������
 * @author kevin
 *
 */
public class GameControl {
	/**
	 * ��Ϸҵ���߼�
	 */
	private GameService gameService;
	
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto gameDto;
	
	/**
	 * ��Ϸ���
	 */
	private GamePanel gamePanel;
	
	/**
	 * ��Ϸ���ô���
	 */
	private GameSetting gameSetting;
	
	/**
	 * ��Ϸ��Ϊ����
	 */
	private HashMap<Integer,Method> actionList;
	
	/**
	 * ��Ϸ�߳�
	 */
	private MoveThread moveThread;
	
	public GameControl(){
		//������Ϸ����Դ
		this.gameDto=new GameDto();
		//������Ϸҵ���߼�����
		this.gameService=new GameServiceImpl(this.gameDto);
		//������Ϸ���
		this.gamePanel=new GamePanel(this.gameDto,this);
		//������Ϸ���ô���
		this.gameSetting=new GameSetting(this);
		//��������
		new GameFrame(this.gamePanel);
		//������Ϸ����
		this.setControlConfig();
	}

	/**
	 * ��Ϸ������Ϊ
	 * @param keyCode ������
	 */
	public void actionByKey(int keyCode) {
		try {
			//�ж���Ϸ�Ƿ�ʧ��
			if(!this.gameDto.isStart()){
				return;
			}
			//�ж��Ƿ������ֵ
			if(this.actionList.containsKey(keyCode)){
				//ִ�з���
				this.actionList.get(keyCode).invoke(this.gameService);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//ˢ�»���
		this.gamePanel.repaint();
	}
	
	/**
	 * ������Ϸ�ؼ�
	 */
	@SuppressWarnings("unchecked")
	private void setControlConfig(){
		//��ʼ����Ϸ��Ϊ����
		this.actionList=new HashMap<Integer, Method>();
		//����������
		ObjectInputStream objectIn=null;
		try {
			//��������������
			objectIn=new ObjectInputStream(new FileInputStream("data/control.dat"));
			//��ȡ������
			HashMap<Integer,String> keyMap=(HashMap<Integer, String>)objectIn.readObject();
			//��Map����ת��ΪSet����
			Set<Entry<Integer,String>> entrySet = keyMap.entrySet();
			//��������
			for(Entry<Integer, String> e:entrySet){
				this.actionList.put(e.getKey(),this.gameService.getClass().getMethod(e.getValue()));
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
	 * ������Ϸ
	 */
	public void startGame() {
		//����Ϸ��ʼʱ�����á���ʼ����ť�޷����
		this.gamePanel.buttonSwitch(false);
		//��ʼ��Ϸ
		this.gameService.startGame();
		//ˢ����Ϸ����
		this.gamePanel.repaint();
		//�����߳�
		this.moveThread=new MoveThread();
		//�����߳�
		this.moveThread.start();
		
	}
	
	/**
	 * �����ƶ��ٶ�
	 */
	public void setSpeed(long speed){
		//�����ٶ�
		this.gameDto.setSpeed(speed);
		//ˢ�»���
		this.gamePanel.repaint();
	}
	
	/**
	 * ��ʾ������ô���
	 */
	public void showPlayerConfig(){
		this.gameSetting.setVisible(true);
	}
	
	/**
	 * �����Ӵ��ں�ˢ�»���
	 */
	public void configOver(){
		//ˢ�»���
		this.gamePanel.repaint();
		//���ؽ���
		this.gamePanel.requestFocus();
		//������Ϸ����
		this.setControlConfig();
	}
	
	/**
	 * ��Ϸʧ�ܺ�Ĵ���
	 */
	private void afterLose() {
		//ʹ��ť���Ե��
		this.gamePanel.buttonSwitch(true);

	}
	
	/**
	 * ��Ϸ�̣߳��ڲ��ࣩ
	 * @author kevin
	 *
	 */
	private class MoveThread extends Thread{
	
		@Override
		public void run() {
			try {
				while(gameDto.isStart()){
					//ˢ�»���
					gamePanel.repaint();
					//ʱ�������ٶȣ�
					Thread.sleep(gameDto.getSpeed());
					//��ͬ�����ƶ�
					gameService.directionMove();
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//ʹ��ť���Ե��
			afterLose();
		}
		
	}
	
}
