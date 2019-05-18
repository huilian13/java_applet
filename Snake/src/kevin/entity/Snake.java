package kevin.entity;

import java.awt.Point;
import java.util.LinkedList;

import kevin.Enum.DirectionEnum;
import kevin.config.GameConfig;

/**
 * ����
 * @author kevin
 *
 */
public class Snake {
	
	/**
	 * �ߵĵ�ǰ����,Ĭ������
	 */
	private int currentDirection=DirectionEnum.RIGHT.getDirection();
	
	/**
	 * �߼���
	 */
	private LinkedList<Point> snakeList;
	
	/**
	 * �߽��С
	 */
	private static final int MIN_X=0;
	
	private static final int MAX_X=GameConfig.getSystemConfig().getMaxX();
	
	private static final int MIN_Y=0;
	
	private static final int MAX_Y=GameConfig.getSystemConfig().getMaxY();
	
	public Snake(){
		//��ʼ���ڵ�
		this.initList();
	}
	
	/**
	 * ��ʼ���ڵ�
	 */
	private void initList(){
		//��ʼ����
		this .snakeList=new LinkedList<Point>();
		//�����ͷ
		this.snakeList.addFirst(new Point(4,4));
	}
	
	/**
	 * ���ƶ�
	 * @param snakeList �ڵ�
	 * @param moveX xƫ����
	 * @param moveY yƫ����
	 * @param newDirection �µķ���
	 * @return true �������ƶ�
	 */
	public boolean snakeMove(int moveX,int moveY,int newDirection){
		//�����ǰ�������·����෴�����ƶ�
		if(currentDirection+newDirection==0){
			return false;
		}
		//��ȡͷ�ڵ�
		Point snakePoint=this.snakeList.getFirst();
		//��������
		int newX=snakePoint.x+moveX;
		int newY=snakePoint.y+moveY;
		//�߽��ж�
		if(this.isCanMove(newX, newY)){
			return false;
		}
		//����µĽڵ�
		this.snakeList.addFirst(new Point(newX,newY));
		//�ı䷽��ֵ
		this.currentDirection=newDirection;
		return true;
	}

	/**
	 * �ж��Ƿ�����ƶ�
	 * @return
	 */
	private boolean isCanMove(int x,int y){
		//�߽��ж�
		return x<MIN_X||x>=MAX_X||y<MIN_Y||y>=MAX_Y;
	}
	
	public LinkedList<Point> getSnakeList() {
		return snakeList;
	}

	public void setSnakeList(LinkedList<Point> snakeList) {
		this.snakeList = snakeList;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

}
