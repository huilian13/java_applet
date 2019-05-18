package kevin.entity;

import kevin.util.MethodUtil;

/**
 * 箱子类（懒羊羊）
 * @author kevin
 *
 */
public class SheepBox {
	
	private int x;
	
	private int y;
    
	public SheepBox(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 箱子移动
	 * @param moveX X轴移动量
	 * @param moveY Y轴移动量
	 */
	public boolean moveBox(int moveX,int moveY,int[][] treeMap){
		int newX=this.x+moveX;
		int newY=this.y+moveY;
		//边界判断,超出边界
		if(!(MethodUtil.IsOverMap(newX,newY,treeMap)&&treeMap[newY][newX]!=2)){
			return false;
		}
		//未超出边界
		this.x=this.x+moveX;
		this.y=this.y+moveY;
		return true;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
