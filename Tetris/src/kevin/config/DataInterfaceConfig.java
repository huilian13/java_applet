package kevin.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
/**
 * ���ݽӿ�������
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class DataInterfaceConfig implements Serializable{
	 /*
	  * �ӿ�ʵ��������
	  */
	 private String className;
	 
	 /**
	  * ����ֵ����
	  */
	 private Map<String,String> paramMap;

	 @SuppressWarnings("unchecked")
	public DataInterfaceConfig(Element dataInterface){
		   //��ȡ�ӿ��������
		   this.className=dataInterface.attributeValue("className");
		   //��ȡ����param��ǩ
		   List<Element> params= dataInterface.elements("param");
		   //��ʼ����������
		   this.paramMap=new HashMap<String, String>();
		   //������ǩ�Ĳ���
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
