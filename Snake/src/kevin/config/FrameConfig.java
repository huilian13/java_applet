package kevin.config;

import org.dom4j.Element;

/**
 * ¥∞ø⁄≈‰÷√
 * @author kevin
 *
 */
public class FrameConfig {
	
	private int width;
	
	private int height;
	
	private int size;
	
	private ButtonConfig buttonConfig;

	public FrameConfig(Element frame){
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.size=Integer.parseInt(frame.attributeValue("size"));
		this.buttonConfig=new ButtonConfig(frame.element("button"));
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSize() {
		return size;
	}

	public ButtonConfig getButtonConfig() {
		return buttonConfig;
	}
	
}
