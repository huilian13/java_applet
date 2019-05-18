package kevin.config;

import java.io.Serializable;

import org.dom4j.Element;
/**
 * ��ť����
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class ButtonConfig implements Serializable{
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
	private int settingX;
	
	/**
	 * ���ð�ť��y����
	 */
	private int settingY;
	
	public ButtonConfig(Element button){
		this.width=Integer.parseInt(button.attributeValue("width"));
		this.height=Integer.parseInt(button.attributeValue("height"));
		//��ȡstart��ǩ
		Element start=button.element("start");
		this.startX=Integer.parseInt(start.attributeValue("x"));
		this.startY=Integer.parseInt(start.attributeValue("y"));
		//��ȡsetting��ǩ
		Element setting=button.element("setting");
		this.settingX=Integer.parseInt(setting.attributeValue("x"));
		this.settingY=Integer.parseInt(setting.attributeValue("y"));
		
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

	public int getSettingX() {
		return settingX;
	}

	public int getSettingY() {
		return settingY;
	}
	
}
