package kevin.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * ���ڽ���
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class FrameConfig implements Serializable{
	/**
	 * ��������
	 */
	private String title;
	
	/**
	 * ���ڿ��
	 */
	private int width;
	
	/**
	 * ���ڸ߶�
	 */
	private int height;
	
	/**
	 * �ڱ߾�
	 */
	private int padding;
	
	/**
	 * �߽�ߴ�
	 */
	private int borderSize;
	
	/**
	 * �����λƫ����
	 */
	private int rectSize;
	
	/**
	 * ����ͼƬ��ĩβid
	 */
	private int loseIndex;
	
	/**
	 * �㴰������
	 */
	private List<LayerConfig> layerConfigs;
	
	/**
	 * ��ť����
	 */
	private ButtonConfig buttonConfig;
	
	public FrameConfig(Element frame){
		//��ȡframe��ǩ������
		this.title = frame.attributeValue("title");
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		this.borderSize=Integer.parseInt(frame.attributeValue("borderSize"));
		this.rectSize=Integer.parseInt(frame.attributeValue("rectSize"));
		this.loseIndex=Integer.parseInt(frame.attributeValue("loseIndex"));
		//��ȡlayer��ǩ����
		this.getLayers(frame);
	}
	
   @SuppressWarnings("unchecked")
	private void getLayers(Element frame){
		//��ȡlayer��ǩ
		List<Element> layerList = frame.elements("layer");
		//��ʼ�������ü���
		this.layerConfigs=new ArrayList<LayerConfig>(layerList.size());
		//����layer��ǩ����
		Element layer=null;
		for(int i=0,length=layerList.size();i<length;i++){
			//��ʼ��layerԪ�ض���
			layer=(Element)layerList.get(i);
			LayerConfig layerConfig=new LayerConfig(layer.attributeValue("className"), 
					                                Integer.parseInt(layer.attributeValue("x")), 
					                                Integer.parseInt(layer.attributeValue("y")), 
					                                Integer.parseInt(layer.attributeValue("width")), 
					                                Integer.parseInt(layer.attributeValue("height")));
			//��ӵ�����
			layerConfigs.add(layerConfig);
		}
		//��ʼ����ť����
		this.buttonConfig=new ButtonConfig(frame.element("button"));
		
		
	}

	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPadding() {
		return padding;
	}

	public int getSize() {
		return borderSize;
	}
    
	public int getRectSize() {
		return rectSize;
	}

	public int getLoseIndex() {
		return loseIndex;
	}

	public List<LayerConfig> getLayerConfigs() {
		return layerConfigs;
	}

	public ButtonConfig getButtonConfig() {
		return buttonConfig;
	}

}
