package kevin.entity;

import java.awt.Point;
import java.util.LinkedList;

import kevin.Enum.DirectionEnum;
import kevin.config.GameConfig;

/**
 * 蛇类
 * @author kevin
 *
 */
public class Snake {
	
	/**
	 * 蛇的当前方向,默认向右
	 */
	private int currentDirection=DirectionEnum.RIGHT.getDirection();
	
	/**
	 * 蛇集合
	 */
	private LinkedList<Point> snakeList;
	
	/**
	 * 边界大小
	 */
	private static final int MIN_X=0;
	
	private static final int MAX_X=GameConfig.getSystemConfig().getMaxX();
	
	private static final int MIN_Y=0;
	
	private static final int MAX_Y=GameConfig.getSystemConfig().getMaxY();
	
	public Snake(){
		//初始化节点
		this.initList();
	}
	
	/**
	 * 初始化节点
	 */
	private void initList(){
		//初始化蛇
		this .snakeList=new LinkedList<Point>();
		//添加蛇头
		this.snakeList.addFirst(new Point(4,4));
	}
	
	/**
	 * 蛇移动
	 * @param snakeList 节点
	 * @param moveX x偏移量
	 * @param moveY y偏移量
	 * @param newDirection 新的方向
	 * @return true 代表能移动
	 */
	public boolean snakeMove(int moveX,int moveY,int newDirection){
		//如果当前方向与新方向相反，则不移动
		if(currentDirection+newDirection==0){
			return false;
		}
		//获取头节点
		Point snakePoint=this.snakeList.getFirst();
		//更新坐标
		int newX=snakePoint.x+moveX;
		int newY=snakePoint.y+moveY;
		//边界判断
		if(this.isCanMove(newX, newY)){
			return false;
		}
		//添加新的节点
		this.snakeList.addFirst(new Point(newX,newY));
		//改变方向值
		this.currentDirection=newDirection;
		return true;
	}

	/**
	 * 判断是否可以移动
	 * @return
	 */
	private boolean isCanMove(int x,int y){
		//边界判断
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
