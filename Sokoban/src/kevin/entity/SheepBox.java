package kevin.entity;

import kevin.util.MethodUtil;

/**
 * �����ࣨ������
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
	 * �����ƶ�
	 * @param moveX X���ƶ���
	 * @param moveY Y���ƶ���
	 */
	public boolean moveBox(int moveX,int moveY,int[][] treeMap){
		int newX=this.x+moveX;
		int newY=this.y+moveY;
		//�߽��ж�,�����߽�
		if(!(MethodUtil.IsOverMap(newX,newY,treeMap)&&treeMap[newY][newX]!=2)){
			return false;
		}
		//δ�����߽�
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
