package kevin.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 游戏配置类
 * @author kevin
 *
 */
public class GameConfig {
	/**
	 * 界面配置
	 */
    private static FrameConfig frameConfig;
    
    /**
     * 系统配置
     */
    private static SystemConfig systemConfig;
    
    /**
     * 读取xml配置文件
     */
    static{
    	try {
			//创建xml读取器
			SAXReader reader=new SAXReader();
			//读取xml文件
			Document doc = reader.read("data/config.xml");
			//读取根标签
			Element game = doc.getRootElement();
			//初始化界面配置
			frameConfig=new FrameConfig(game.element("frame"));
			//初始化系统配置
			systemConfig=new SystemConfig(game.element("system"));
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
	
}
