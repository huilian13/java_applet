package kevin.entity;

import kevin.util.MethodUtil;

/**
 * 人物类
 * @author kevin
 *
 */
public class Wolf {
	
	/**
	 * X坐标
	 */
    private int x;
     
    /**
     * Y坐标
     */
    private int y;
     
	public Wolf(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * 人物移动
	 * @param moveX X轴移动量
	 * @param moveY Y轴移动量
	 */
	public void move(int moveX,int moveY,int[][] treeMap){
		int newX=this.x+moveX;
		int newY=this.y+moveY;
		//边界判断,超出边界
		if(!(MethodUtil.IsOverMap(newX,newY,treeMap))){
			return;
		}
		//未超出边界
		this.x=this.x+moveX;
		this.y=this.y+moveY;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
     
}
