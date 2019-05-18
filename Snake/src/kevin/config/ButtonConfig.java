package kevin.config;

import org.dom4j.Element;

/**
 * ��ť����
 * @author kevin
 *
 */
public class ButtonConfig {
	/**
	 * ��ť�Ŀ��
	 */
	private int width;
	
	/**
	 * ��ť�ĸ߶�
	 */
	private int height;
	
	/**
	 * ��ʼ��ť��x����
	 */
	private int startX;
	
	/**
	 * ��ʼ��ť��y����
	 */
	private int startY;
	
	/**
	 * ���ð�ť��x����
	 */
	private int configX;
	
	/**
	 * ���ð�ť��y����
	 */
	private int configY;
	
	public ButtonConfig(Element button){
		//��ȡ��ť�Ŀ��
		this.width=Integer.parseInt(button.attributeValue("width"));
		//��ȡ��ť�ĸ߶�
		this.height=Integer.parseInt(button.attributeValue("height"));
		//��ȡ��ʼ��ť������
		this.getStartConfig(button.element("start"));
		//��ȡ���ð�ť������
		this.getSettingConfig(button.element("setting"));
		
	}
	
	/**
	 * ��ȡ��ʼ��ť������
	 */
	private void getStartConfig(Element start){
		this.startX=Integer.parseInt(start.attributeValue("x"));
		this.startY=Integer.parseInt(start.attributeValue("y"));
	}
	
	/**
	 * ��ȡ���ð�ť������
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
