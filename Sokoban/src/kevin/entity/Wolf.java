package kevin.entity;

import kevin.util.MethodUtil;

/**
 * ������
 * @author kevin
 *
 */
public class Wolf {
	
	/**
	 * X����
	 */
    private int x;
     
    /**
     * Y����
     */
    private int y;
     
	public Wolf(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * �����ƶ�
	 * @param moveX X���ƶ���
	 * @param moveY Y���ƶ���
	 */
	public void move(int moveX,int moveY,int[][] treeMap){
		int newX=this.x+moveX;
		int newY=this.y+moveY;
		//�߽��ж�,�����߽�
		if(!(MethodUtil.IsOverMap(newX,newY,treeMap))){
			return;
		}
		//δ�����߽�
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
