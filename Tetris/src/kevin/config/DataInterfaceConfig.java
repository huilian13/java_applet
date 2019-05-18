package kevin.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
/**
 * 数据接口配置类
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class DataInterfaceConfig implements Serializable{
	 /*
	  * 接口实现类名称
	  */
	 private String className;
	 
	 /**
	  * 参数值集合
	  */
	 private Map<String,String> paramMap;

	 @SuppressWarnings("unchecked")
	public DataInterfaceConfig(Element dataInterface){
		   //获取接口类的名称
		   this.className=dataInterface.attributeValue("className");
		   //获取所有param标签
		   List<Element> params= dataInterface.elements("param");
		   //初始化参数集合
		   this.paramMap=new HashMap<String, String>();
		   //遍历标签的参数
		   for(Element e:params){
			   this.paramMap.put(e.attributeValue("key"),e.attributeValue("value"));
		   }
	 }

	public String getClassName() {
		return className;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}
	 
	 
}
