package kevin.config;

import org.dom4j.Element;

/**
 * 系统配置类
 * @author kevin
 *
 */
public class SystemConfig {
	
	private int minX;
	
	/**
	 * 最大x坐标
	 */
	private int maxX;
	
	private int minY;
	
	/**
	 * 最大y坐标
	 */
	private int maxY;
	
	public SystemConfig(Element system) {
		//获取x坐标值
		this.minX=Integer.parseInt(system.attributeValue("minX"));
		this.maxX=Integer.parseInt(system.attributeValue("maxX"));
		//获取y坐标值
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
