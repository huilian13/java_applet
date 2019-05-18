package kevin.config;

import org.dom4j.Element;

/**
 * 按钮配置
 * @author kevin
 *
 */
public class ButtonConfig {
	/**
	 * 按钮的宽度
	 */
	private int width;
	
	/**
	 * 按钮的高度
	 */
	private int height;
	
	/**
	 * 开始按钮的x坐标
	 */
	private int startX;
	
	/**
	 * 开始按钮的y坐标
	 */
	private int startY;
	
	/**
	 * 设置按钮的x坐标
	 */
	private int configX;
	
	/**
	 * 设置按钮的y坐标
	 */
	private int configY;
	
	public ButtonConfig(Element button){
		//获取按钮的宽度
		this.width=Integer.parseInt(button.attributeValue("width"));
		//获取按钮的高度
		this.height=Integer.parseInt(button.attributeValue("height"));
		//获取开始按钮的坐标
		this.getStartConfig(button.element("start"));
		//获取配置按钮的坐标
		this.getSettingConfig(button.element("setting"));
		
	}
	
	/**
	 * 获取开始按钮的坐标
	 */
	private void getStartConfig(Element start){
		this.startX=Integer.parseInt(start.attributeValue("x"));
		this.startY=Integer.parseInt(start.attributeValue("y"));
	}
	
	/**
	 * 获取配置按钮的坐标
	 */
	private void getSettingConfig(Element config){
		this.configX=Integer.parseInt(config.attributeValue("x"));
		this.configY=Integer.parseInt(config.attributeValue("y"));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getConfigX() {
		return configX;
	}

	public int getConfigY() {
		return configY;
	}
	
}
