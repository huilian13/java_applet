package kevin.config;

import org.dom4j.Element;

/**
 * ϵͳ����
 * @author kevin
 *
 */
public class SystemConfig {
	/**
	 * ���x����
	 */
	private int maxX;
	
	/**
	 * ���y����
	 */
	private int maxY;
	
	/**
	 * �ϰ�����
	 */
	private int rockNum;
	
	/**
	 * �����ٶ�
	 */
	private int slowSpeed;
	
	/**
	 * �����ٶ�
	 */
	private int normalSpeed;
	
	/**
	 * ����ٶ�
	 */
	private int fastSpeed;
	
	public SystemConfig(Element system){
		//��ȡ���x����
		this.maxX=Integer.parseInt(system.attributeValue("maxX"));
		//��ȡ���y����
		this.maxY=Integer.parseInt(system.attributeValue("maxY"));
		//��ȡ�ϰ�����
		this.rockNum=Integer.parseInt(system.attributeValue("rockNum"));
		//��ȡ�ٶ�
		this.getSpeed(system.element("speed"));
	}
	
	/**
	 * ��ȡ�ٶ�
	 */
	private void getSpeed(Element speed){
		this.slowSpeed=Integer.parseInt(speed.attributeValue("slow"));
		this.normalSpeed=Integer.parseInt(speed.attributeValue("normal"));
		this.fastSpeed=Integer.parseInt(speed.attributeValue("fast"));
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getRockNum() {
		return rockNum;
	}

	public int getSlowSpeed() {
		return slowSpeed;
	}

	public int getNormalSpeed() {
		return normalSpeed;
	}

	public int getFastSpeed() {
		return fastSpeed;
	}
	
}
