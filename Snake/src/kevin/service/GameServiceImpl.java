package kevin.service;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import kevin.Enum.DirectionEnum;
import kevin.dto.GameDto;
import kevin.entity.Snake;
import kevin.factory.GameFactory;

/**
 * ��Ϸҵ���߼�ʵ����
 * @author kevin
 *
 */
public class GameServiceImpl implements GameService{
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto gameDto;
	
	/**
	 * �ƶ������ֵ��
	 */
	private HashMap<Integer,Method> directionMap;
	
	/**
	 * �߽ڵ�
	 */
	private LinkedList<Point> snakeList;
	
	/**
	 * �������·��
	 */
	private static final String PATH=GameFactory.getDataMap().get("direction");
	
	public GameServiceImpl(GameDto gameDto) {
		//��ʼ������Դ
		this.gameDto = gameDto;
		//��ʼ����ֵ��
		this.InitDirectionMap();
		
	}

	/**
	 * ��ʼ�������ֵ��
	 */
	@SuppressWarnings("unchecked")
	private void InitDirectionMap(){
		//��������(������ӳ�䵽������)
		this.directionMap=new HashMap<Integer, Method>();
		//����������
		ObjectInputStream objectIn=null;
		try {
			//��������������
			objectIn=new ObjectInputStream(new FileInputStream(PATH));
			//��ȡ�ļ�
			HashMap<Integer,String> map=(HashMap<Integer,String>)objectIn.readObject();
			//��map����ת��Ϊset����
			Set<Entry<Integer, String>> entrySet = map.entrySet();
			for(Entry<Integer,String> entry:entrySet){
				//����ֵ��ӵ����򼯺���
				this.directionMap.put(entry.getKey(),this.getClass().getMethod(entry.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				objectIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void keyUp() {
		//��ͣʱ�޷�����
		if(this.gameDto.isPause()){
			return;
		}
		//�ƶ�
		boolean canMove = this.gameDto.getSnake().snakeMove(0,-1,DirectionEnum.UP.getDirection());
		//��ȡ�߽ڵ�	
		this.snakeList=this.gameDto.getSnake().getSnakeList();
		//����Ե�ʳ��������µ�ʳ��
		if(this.isEatFood(snakeList)){
			//�Ե�ʳ���5��
			this.gameDto.setSorce(this.gameDto.getSorce()+5);
			//����ʳ��
			this.gameDto.createFood();
			return;
		}
		//������ƶ�
		if(canMove){
			//û�гԵ�ʳ���ȥ��ĩβ�ڵ�
			snakeList.removeLast();
		}
		//�����Ϸ״̬
		this.checkGameOver(snakeList);
	}

	@Override
	public void keyDown() {
		//��ͣʱ�޷�����
		if(this.gameDto.isPause()){
			return;
		}
		//�ƶ�
		boolean canMove = this.gameDto.getSnake().snakeMove(0,1,DirectionEnum.DOWN.getDirection());
		//��ȡ�߽ڵ�
		this.snakeList=this.gameDto.getSnake().getSnakeList();
		//����Ե�ʳ��������µ�ʳ��
		if(this.isEatFood(snakeList)){
			//�Ե�ʳ���5��
			this.gameDto.setSorce(this.gameDto.getSorce()+5);
			//����ʳ��
			this.gameDto.createFood();
			return;
		}
		//������ƶ�
		if(canMove){
			//û�гԵ�ʳ���ȥ��ĩβ�ڵ�
			snakeList.removeLast();
		}
		//�����Ϸ״̬
		this.checkGameOver(snakeList);
	}

	@Override
	public void keyLeft() {
		//��ͣʱ�޷�����
		if(this.gameDto.isPause()){
			return;
		}
		//�ƶ�
		boolean canMove = this.gameDto.getSnake().snakeMove(-1,0,DirectionEnum.LEFT.getDirection());
		//��ȡ�߽ڵ�
		this.snakeList=this.gameDto.getSnake().getSnakeList();
		//����Ե�ʳ��������µ�ʳ��
		if(this.isEatFood(snakeList)){
			//�Ե�ʳ���5��
			this.gameDto.setSorce(this.gameDto.getSorce()+5);
			//����ʳ��
			this.gameDto.createFood();
			return;
		}
		//������ƶ�
		if(canMove){
			//û�гԵ�ʳ���ȥ��ĩβ�ڵ�
			snakeList.removeLast();
		}
		//�����Ϸ״̬
		this.checkGameOver(snakeList);
	}

	@Override
	public void keyRight() {
		//��ͣʱ�޷�����
		if(this.gameDto.isPause()){
			return;
		}
		//�ƶ�
		boolean canMove= this.gameDto.getSnake().snakeMove(1,0,DirectionEnum.RIGHT.getDirection());
		//��ȡ�߽ڵ�
		this.snakeList=this.gameDto.getSnake().getSnakeList();
		//����Ե�ʳ��������µ�ʳ��
		if(this.isEatFood(snakeList)){
			//�Ե�ʳ���5��
			this.gameDto.setSorce(this.gameDto.getSorce()+5);
			//����ʳ��
			this.gameDto.createFood();
			return;
		}
		//������ƶ�
		if(canMove){
			//û�гԵ�ʳ���ȥ��ĩβ�ڵ�
			snakeList.removeLast();
		}
		//�����Ϸ״̬
		this.checkGameOver(snakeList);
		
	}

	@Override
	public void keyFun() {
		//��ͣ
		this.gameDto.changePause();
	}

	@Override
	public void startGame() {
		//����Ϸ����Ϊ��ʼ
		this.gameDto.setStart(true);
		//�����߶���
		this.gameDto.setSnake(new Snake());
		//��ʼ�����е�����Դ
		this.gameDto.initEntityParam();
		
	}
	
	@Override
	public void directionMove() {
		try {
			//��ȡ����ֵ
			int currentDirection = this.gameDto.getSnake().getCurrentDirection();
			//���ݷ���ֵ�ƶ�
			if(this.directionMap.containsKey(currentDirection)){
				//ִ�з���
				this.directionMap.get(currentDirection).invoke(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * �Ե�ʳ��
	 */
	private boolean isEatFood(LinkedList<Point> snakeList){
		//��ȡ��ͷ
		Point snakeHead = snakeList.getFirst();
		//��ȡʳ������
		Point foodPoint = this.gameDto.getFood().getfoodPoint();
		//�����ͷ��ʳ���غϣ��ʹ���Ե�ʳ��
		if(foodPoint.equals(snakeHead)){
			return true;
		}
		//û�гԵ�ʳ��
		return false;
	}
	
	/**
	 * �����Ϸ
	 */
	private void checkGameOver(LinkedList<Point> snakeList){
		//�����Ϸ�Ƿ�ʧ��
		if(this.isGameOver(snakeList)){
			//��Ϸʧ��
			this.gameDto.setStart(false);
		}
	}
	
	/**
	 * �ж���Ϸ�Ƿ�ʧ��
	 * @return
	 */
	private boolean isGameOver(LinkedList<Point> snakeList){
		//��ȡͷ�ڵ�
		Point snakeHead = snakeList.getFirst();
		//��ȡ��Ϸ��ͼ��Ϣ
		boolean[][] gameMap = this.gameDto.getGameMap();
		if(gameMap[snakeHead.x][snakeHead.y]){
			//��Ϸ����
			return true;
		}
		
		for(int i=1,length=snakeList.size();i<length;i++){
			if(snakeHead.equals(snakeList.get(i))){
				return true;
			}
		}
		//��Ϸ����
		return false;
	}

}
