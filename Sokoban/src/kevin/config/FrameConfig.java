package kevin.config;

import org.dom4j.Element;
/**
 * 界面配置类
 * @author kevin
 *
 */
public class FrameConfig {
	/**
	 * 界面宽度
	 */
	private int width;
	
	/**
	 * 界面高度
	 */
	private int height;
	
	/**
	 * 界面标题
	 */
	private String title;
	
	/**
	 * 尺寸大小
	 */
	private int size;
	
	public FrameConfig(Element frame){
		//获取各属性值
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.title=frame.attributeValue("title");
		this.size=Integer.parseInt(frame.attributeValue("size"));
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public int getSize() {
		return size;
	}
}
