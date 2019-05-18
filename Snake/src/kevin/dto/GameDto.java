package kevin.dto;

import java.awt.Point;
import java.util.Random;

import kevin.config.GameConfig;
import kevin.entity.Food;
import kevin.entity.Snake;
/**
 * ��Ϸ����Դ
 * @author kevin
 *
 */
public class GameDto {
	
	/**
	 * �߶���
	 */
	private Snake snake;
	
	/**
	 * ʳ��
	 */
	private Food food;
	
	/**
	 * ��Ϸ��ͼ
	 */
	private boolean[][] gameMap;
	
	/**
	 * �ƶ��ٶ�
	 */
	private long speed;
	
	/**
	 * ��Ϸ��ʼ
	 */
	private boolean isStart;
	
	/**
	 * ��ͣ
	 */
	private boolean isPause;
	
	/**
	 * �÷�
	 */
	private int sorce;
	
	/**
	 * ��ͼ������
	 */
	public static final int MAP_ROW=GameConfig.getSystemConfig().getMaxX();
	
	/**
	 * ��ͼ������
	 */
	public static final int MAP_COL=GameConfig.getSystemConfig().getMaxY();
	
	/**
	 * �ϰ�����
	 */
	private static final int ROCK_NUM=GameConfig.getSystemConfig().getRockNum();
	
	/**
	 * �����������
	 */
	private Random random=new Random();
	
	public GameDto(){
		//��ʼ��ʵ������
		this.initEntityParam();
	}
	
	/**
	 * ��ʼ��ʵ������
	 */
	public void initEntityParam(){
		//��ʼ����Ϸ��ͼ
		this.initGameMap();
		//����ʳ��
		this.createFood();
		//Ĭ��δ��ͣ
		this.isPause=false;
		//��ʼ������
		this.sorce=0;
		//Ĭ���ƶ��ٶ�Ϊ1��������ģʽ��
		this.speed=1000;
	}
	
	/**
	 * ��ʼ����Ϸ��ͼ
	 */
	private void initGameMap(){
		//��ʼ����Ϸ��ͼ
		this.gameMap=new boolean[MAP_ROW][MAP_COL];
		//���õ�ͼ�߽�
		for(int x=0;x<MAP_ROW;x++){
			for(int y=0;y<MAP_COL;y++){
				if(x==0||x==MAP_ROW-1||y==0||y==MAP_COL-1){
					gameMap[x][y]=true;
				}
			}
		}
		
		//����Ϸ��ʼʱ���������ϰ���
		if(!this.isStart){
			return;
		}
		//�����ϰ���
		int count=0;
		while(count<ROCK_NUM){
			int x=random.nextInt(MAP_ROW);
			int y=random.nextInt(MAP_COL);
			if(gameMap[x][y]||(x==4&&y==4)){
				continue;
			}
			gameMap[x][y]=true;
			count++;
		}
	}
	
	/**
	 * ����ʳ��
	 */
	public void createFood(){
		//��������
		int x,y;
		while(true){
			//ʳ������
			x=random.nextInt(MAP_ROW);
			y=random.nextInt(MAP_COL);
			//�ж�ʳ���Ƿ�������ש����
			if(gameMap[x][y]){
				continue;
			}
			//����ʳ��
			this.food=new Food(new Point(x,y));
			break;
		}
	}
	
	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	
	public long getSpeed() {
		return speed;
	}
	
	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isPause() {
		return isPause;
	}

	public void changePause() {
		this.isPause =!this.isPause;
	}

	public int getSorce() {
		return sorce;
	}

	public void setSorce(int sorce) {
		this.sorce = sorce;
	}

	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
}
