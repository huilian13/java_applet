package kevin.config;

import org.dom4j.Element;

/**
 * 系统配置
 * @author kevin
 *
 */
public class SystemConfig {
	/**
	 * 最大x坐标
	 */
	private int maxX;
	
	/**
	 * 最大y坐标
	 */
	private int maxY;
	
	/**
	 * 障碍物数
	 */
	private int rockNum;
	
	/**
	 * 最慢速度
	 */
	private int slowSpeed;
	
	/**
	 * 正常速度
	 */
	private int normalSpeed;
	
	/**
	 * 最快速度
	 */
	private int fastSpeed;
	
	public SystemConfig(Element system){
		//获取最大x坐标
		this.maxX=Integer.parseInt(system.attributeValue("maxX"));
		//获取最大y坐标
		this.maxY=Integer.parseInt(system.attributeValue("maxY"));
		//获取障碍物数
		this.rockNum=Integer.parseInt(system.attributeValue("rockNum"));
		//获取速度
		this.getSpeed(system.element("speed"));
	}
	
	/**
	 * 获取速度
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
