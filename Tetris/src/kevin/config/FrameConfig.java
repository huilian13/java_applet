package kevin.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * 窗口界面
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class FrameConfig implements Serializable{
	/**
	 * 窗口名称
	 */
	private String title;
	
	/**
	 * 窗口宽度
	 */
	private int width;
	
	/**
	 * 窗口高度
	 */
	private int height;
	
	/**
	 * 内边距
	 */
	private int padding;
	
	/**
	 * 边界尺寸
	 */
	private int borderSize;
	
	/**
	 * 方块的位偏移量
	 */
	private int rectSize;
	
	/**
	 * 方块图片的末尾id
	 */
	private int loseIndex;
	
	/**
	 * 层窗口属性
	 */
	private List<LayerConfig> layerConfigs;
	
	/**
	 * 按钮属性
	 */
	private ButtonConfig buttonConfig;
	
	public FrameConfig(Element frame){
		//获取frame标签的属性
		this.title = frame.attributeValue("title");
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		this.borderSize=Integer.parseInt(frame.attributeValue("borderSize"));
		this.rectSize=Integer.parseInt(frame.attributeValue("rectSize"));
		this.loseIndex=Integer.parseInt(frame.attributeValue("loseIndex"));
		//获取layer标签属性
		this.getLayers(frame);
	}
	
   @SuppressWarnings("unchecked")
	private void getLayers(Element frame){
		//获取layer标签
		List<Element> layerList = frame.elements("layer");
		//初始化层配置集合
		this.layerConfigs=new ArrayList<LayerConfig>(layerList.size());
		//声明layer标签对象
		Element layer=null;
		for(int i=0,length=layerList.size();i<length;i++){
			//初始化layer元素对象
			layer=(Element)layerList.get(i);
			LayerConfig layerConfig=new LayerConfig(layer.attributeValue("className"), 
					                                Integer.parseInt(layer.attributeValue("x")), 
					                                Integer.parseInt(layer.attributeValue("y")), 
					                                Integer.parseInt(layer.attributeValue("width")), 
					                                Integer.parseInt(layer.attributeValue("height")));
			//添加到集合
			layerConfigs.add(layerConfig);
		}
		//初始化按钮属性
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
