package kevin.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 游戏配置
 * @author kevin
 *
 */
public class GameConfig {
	/**
	 * 窗口配置
	 */
	private static FrameConfig frameConfig=null;
	
	/**
	 * 系统配置
	 */
	private static SystemConfig systemConfig=null;
	
	/**
	 * 数据配置
	 */
	private static DataConfig dataConfig=null;

	/**
	 * 配置文件路径
	 */
	private static final String PATH="data/config.xml";
	
	private GameConfig(){}
	
	static{
		try {
			//创建文件读取器
			SAXReader reader=new SAXReader();
			//读取xml文件
			Document doc = reader.read(PATH);
			//获取根元素
			Element root = doc.getRootElement();
			//初始化窗口配置
			frameConfig=new FrameConfig(root.element("frame"));
			//初始化系统配置
			systemConfig=new SystemConfig(root.element("system"));
			//初始化数据配置
			dataConfig=new DataConfig(root.element("data"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FrameConfig getFrameConfig() {
		return frameConfig;
	}

	public static SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public static DataConfig getDataConfig() {
		return dataConfig;
	}

}
