package kevin.config;

import org.dom4j.Element;
/**
 * ����������
 * @author kevin
 *
 */
public class FrameConfig {
	/**
	 * ������
	 */
	private int width;
	
	/**
	 * ����߶�
	 */
	private int height;
	
	/**
	 * �������
	 */
	private String title;
	
	/**
	 * �ߴ��С
	 */
	private int size;
	
	public FrameConfig(Element frame){
		//��ȡ������ֵ
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
