package kevin.entity;

import java.awt.Point;

/**
 * ʳ����
 * @author kevin
 *
 */
public class Food{
	
	/**
	 * ʳ�������
	 */
	private Point foodPoint;

	public Food(Point foodPoint) {
		this.foodPoint = foodPoint;
	}

	public Point getfoodPoint() {
		return foodPoint;
	}

	public void setfoodPoint(Point foodPoint) {
		this.foodPoint = foodPoint;
	}
}
