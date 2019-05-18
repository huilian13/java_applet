package kevin.config;

import java.util.HashMap;
import java.util.List;

import org.dom4j.Element;

public class DataConfig {
	
	/**
	 * ���ݼ���
	 */
	private HashMap<String,String> dataMap;

	public DataConfig(Element data){
		//��ʼ�����ݼ���
		this.dataMap=new HashMap<String, String>();
		//��ȡ����path�ı�ǩ
		@SuppressWarnings("unchecked")
		List<Element> pathList = data.elements("path");
		//��ӵ�������
		for(Element e:pathList){
			this.dataMap.put(e.attributeValue("key"),e.attributeValue("value"));
		}
	}

	public HashMap<String, String> getDataMap() {
		return dataMap;
	}
	
}
