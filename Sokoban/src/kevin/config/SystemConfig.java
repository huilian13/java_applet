package kevin.config;

import org.dom4j.Element;

/**
 * ϵͳ������
 * @author kevin
 *
 */
public class SystemConfig {
	
	private int minX;
	
	/**
	 * ���x����
	 */
	private int maxX;
	
	private int minY;
	
	/**
	 * ���y����
	 */
	private int maxY;
	
	public SystemConfig(Element system) {
		//��ȡx����ֵ
		this.minX=Integer.parseInt(system.attributeValue("minX"));
		this.maxX=Integer.parseInt(system.attributeValue("maxX"));
		//��ȡy����ֵ
		this.minY=Integer.parseInt(system.attributeValue("minY"));
		this.maxY=Integer.parseInt(system.attributeValue("maxY"));
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}

}
