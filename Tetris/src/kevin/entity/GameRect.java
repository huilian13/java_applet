package kevin.entity;

import java.awt.Point;
import java.util.List;

import kevin.config.GameConfig;
/**
 * ��Ϸ����ʵ��
 * @author kevin
 *
 */
public class GameRect {

	/**
	 * ��������
	 */
	private Point[] rectPoint;
	
	/**
	 * ������
	 */
	private int rectCode;
	
	/**
	 * ��ͼ�ı߽��С
	 */
	private static final int MIN_X=0;
	
	private static final int MAX_X=GameConfig.getSystemConfig().getMaxX();
	
	private static final int MIN_Y=0;
	
	private static final int MAX_Y=GameConfig.getSystemConfig().getMaxY();
	
	/**
	 * �������ͼ���
	 */
	private static final List<Point[]> RECT_TYPE=GameConfig.getSystemConfig().getRectPoints();
	
	/**
	 * ��ת���ͼ���
	 */
	private static final List<Boolean> ROUND_TYPE=GameConfig.getSystemConfig().getRoundType();

	public GameRect(int rectCode){
		//��ʼ������
		this.initRect(rectCode);
		
		
	}
	
	/**
	 * ��������
	 */
	public void initRect(int rectCode){
		//�洢������
		this.rectCode=rectCode;
		//��ʼ������
		Point[] rect = RECT_TYPE.get(rectCode);
		//��ʼ����������
		this.rectPoint=new Point[rect.length];
		for(int i=0,length=rect.length;i<length;i++){
			//��point���������¡������������
			this.rectPoint[i]=(Point)rect[i].clone();
		}
	}
    
	/**
	 * �����ƶ�
	 */
	public boolean rectMove(int moveX,int moveY,boolean[][] gameMap){
		//�����������������Ϊ��ʱ����
		Point rect=null;
		//���������µ�x���꣬��Ϊ��ʱ����
		int newX=0;
		//���������µ�y���꣬��Ϊ��ʱ����
		int newY=0;
		//�ƶ������ж��Ƿ�Խ��
		for(int i=0,length=this.rectPoint.length;i<length;i++){
			//��ȡ�����������
			rect=rectPoint[i];
			//���������µ�x����
			newX=rect.x+moveX;
			//���������µ�y����
			newY=rect.y+moveY;
			//�߽��ж�
			if(this.isOverMap(newX, newY,gameMap)){
				return false;
			}
		}
		
		//��������ƶ�
		for(int i=0,length=this.rectPoint.length;i<length;i++){
			rect=rectPoint[i];
			rect.x+=moveX;
			rect.y+=moveY;
		}
		return true;
	}
	
	/**
	 * ������ת
	 */
	public void rectRound(boolean[][] gameMap){
		//�����������������Ϊ��ʱ����
		Point rect=null;
		//���������µ�x���꣬��Ϊ��ʱ����
		int newX=0;
		//���������µ�y���꣬��Ϊ��ʱ����
		int newY=0;
		//����Ϊ��ɫʱ����ת
		if(!ROUND_TYPE.get(this.rectCode)){
			return;
		}
		for(int i=1,length=this.rectPoint.length;i<length;i++){
			//��ȡ�������
			rect=this.rectPoint[i];
			//����������
			newX=this.rectPoint[0].y+this.rectPoint[0].x-rect.y;
			newY=this.rectPoint[0].y-this.rectPoint[0].x+rect.x;
			//�߽��ж�
			if(this.isOverMap(newX, newY,gameMap)){
				return;
			}
		}
		//������ת
		for(int i=1,length=this.rectPoint.length;i<length;i++){
			//��ȡ�������
			rect=this.rectPoint[i];
			//����������
			newX=this.rectPoint[0].y+this.rectPoint[0].x-rect.y;
			newY=this.rectPoint[0].y-this.rectPoint[0].x+rect.x;
			rect.x=newX;
			rect.y=newY;
		}
	}
	
	/**
	 * �߽��ж�
	 * @param x �����x����
	 * @param y �����y����
	 * @return  true����Խ��
	 */
	private boolean isOverMap(int x,int y,boolean[][] gameMap){
		return x<MIN_X||x>=MAX_X||y<MIN_Y||y>=MAX_Y||gameMap[y][x];
	}
	
	/**
	 * ��ȡ������
	 * @return ���
	 */
	public int getRectCode() {
		return rectCode;
	}

	/**
	 * ��ȡ��������
	 * @return �������
	 */
	public Point[] getRectPoint() {
		return rectPoint;
	}

}
