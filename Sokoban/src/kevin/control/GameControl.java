package kevin.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import kevin.dto.GameDto;
import kevin.service.GameServiceImpl;
import kevin.service.GameService;
import kevin.ui.GameFinishFrame;
import kevin.ui.GameFrame;
import kevin.ui.GameImage;
import kevin.ui.GamePanel;

/**
 * ��Ϸ���Ʋ�
 * @author kevin
 *
 */
public class GameControl {
	/**
	 * ��Ϸҵ���߼�
	 */
	private GameService gameService;
	
	/**
	 * ��Ϸ���
	 */
	private GamePanel gamePanel;
	
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto gameDto;
	
	/**
	 * ��Ϸ��ɴ���
	 */
	private GameFinishFrame gameFinishFrame;
	
	/**
	 * ��Ϊ����
	 */
	private Map<Integer,Method> actionList;
	
	/**
	 * ��Ϸ�������ݵ�·��
	 */
	private static final String CONTORL_PATH="data/control.dat";
	
	public GameControl(){
		//������Ϸ����Դ
		this.gameDto=new GameDto();
		//����ҵ���߼�
		this.gameService=new GameServiceImpl(gameDto);
		//������Ϸ���
		this.gamePanel=new GamePanel(gameDto,this);
		//��ʼ����Ϸ��ɴ���
		this.gameFinishFrame=new GameFinishFrame(this);
		//������Ϸ����
		new GameFrame(this.gamePanel);
		//��ʼ����Ϊ����
		this.initActionList();
		
	}

	/**
	 * ��ʼ����Ϊ����
	 */
	@SuppressWarnings("unchecked")
	private void initActionList(){
		//����������
		ObjectInputStream in=null;
		try {
			//��ʼ����Ϊ����
			this.actionList=new HashMap<Integer,Method>();
			//��������������
			in=new ObjectInputStream(new FileInputStream(CONTORL_PATH));
			//��ȡ�ļ�����
			HashMap<Integer,String> keysMap=(HashMap<Integer,String>)in.readObject();
			//��map����ת����set����
			Set<Entry<Integer,String>> entrySet = keysMap.entrySet();
			for(Entry<Integer,String> entry:entrySet){
				this.actionList.put(entry.getKey(),this.gameService.getClass().getMethod(entry.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��Ϸ������Ϊ
	 * @param keyCode
	 */
	public void actionKeys(int keyCode) {
		try {
			//�����Ϸ������,�����Ϸ��ɣ���ʾ����
			if(this.gameService.gameFinish()){
				this.gameFinishFrame.setVisible(true);
				return;
			}
			//�ж��Ƿ�����Ч��
			if(this.actionList.containsKey(keyCode)){
				//��Ϸ����
				this.actionList.get(keyCode).invoke(this.gameService);
			}
			//ˢ�½���
			this.gamePanel.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 д�������ļ�control.dat
		 switch(keyCode){
		case KeyEvent.VK_UP:
			this.gameService.keyUp();
			break;
		case KeyEvent.VK_DOWN:
			this.gameService.keyDown();
			break;
		case KeyEvent.VK_LEFT:
			this.gameService.keyLeft();
			break;
		case KeyEvent.VK_RIGHT:
			this.gameService.keyRight();
			break;
		}*/
	}

	/**
	 * �ر��Ӵ���
	 */
	public void setOver() {
		// �ر��Ӵ���
		this.gameFinishFrame.setVisible(false);
		//ˢ����Ϸ����
		this.gamePanel.repaint();
	}

	/**
	 * ��ʼ��һ�ص���Ϸ
	 */
	public void startNextGame(int index) {
		//��ȡƤ��·��
		File file = this.gameDto.getFiles().get(index);
		//���µ�ͼ
		GameImage.setGameSkin(file.getName());
		//��ʼ������Դ
		this.gameDto.initDto(index);
		//�ر��Ӵ���
		this.setOver();
		
	}
	
}
