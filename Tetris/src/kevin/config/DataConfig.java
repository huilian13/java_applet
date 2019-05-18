package kevin.config;

import java.io.Serializable;

import org.dom4j.Element;
/**
 * 游戏数据配置类
 * @author kevin
 *
 */
@SuppressWarnings("serial")
public class DataConfig implements Serializable{
	
	/**
	 * 最大行数
	 */
	private int maxRow;
	
	/**
	 * 数据库接口配置
	 */
	private DataInterfaceConfig dataBase;
	
	/**
	 * 本地数据接口配置
	 */
	private DataInterfaceConfig dataDisk;
	
	public DataConfig(Element data){
		//获取最大行数
		this.maxRow=Integer.parseInt(data.attributeValue("maxRow"));
		//初始化数据库接口
		this.dataBase=new DataInterfaceConfig(data.element("dataBase"));
		//初始化本地数据接口
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
