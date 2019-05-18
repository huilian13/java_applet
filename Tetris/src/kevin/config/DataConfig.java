package kevin.config;

import java.io.Serializable;

import org.dom4j.Element;
/**
 * ��Ϸ����������
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class DataConfig implements Serializable{
	
	/**
	 * �������
	 */
	private int maxRow;
	
	/**
	 * ���ݿ�ӿ�����
	 */
	private DataInterfaceConfig dataBase;
	
	/**
	 * �������ݽӿ�����
	 */
	private DataInterfaceConfig dataDisk;
	
	public DataConfig(Element data){
		//��ȡ�������
		this.maxRow=Integer.parseInt(data.attributeValue("maxRow"));
		//��ʼ�����ݿ�ӿ�
		this.dataBase=new DataInterfaceConfig(data.element("dataBase"));
		//��ʼ���������ݽӿ�
		this.dataDisk=new DataInterfaceConfig(data.element("dataDisk"));
	}
 
	public int getMaxRow() {
		return maxRow;
	}

	public DataInterfaceConfig getDataBase() {
		return dataBase;
	}

	public DataInterfaceConfig getDataDisk() {
		return dataDisk;
	}
	
}
