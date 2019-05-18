package kevin.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
	/**
	 * 私有化构造函数，无法创建对象
	 */
	private GameConfig(){}
	
	/**
	 * 游戏界面配置
	 */
    private static FrameConfig frameConfig=null;
    
    /**
     * 游戏数据配置
     */
    private static DataConfig dataConfig=null;
    
    /**
     * 游戏数据配置
     */
    private static SystemConfig systemConfig=null;
    
    /**
     * 配置文件路径
     */
    private static final String PATH="data/config.xml";
    
    private static final boolean IS_DEBUG=false;
    
    /**
     * 静态初始化
     */
    static{
	    	try {
	    		if(IS_DEBUG){
	    			//获取文件读取器
	    			SAXReader reader=new SAXReader();
	    			//读取xml文件
	    			Document doc= reader.read(new File(PATH));
	    			//读取根元素
	    			Element gameRoot = doc.getRootElement();
	    			//创建游戏界面配置对象
	    			frameConfig=new FrameConfig(gameRoot.element("frame"));
	    			//创建游戏数据配置对象
	    			dataConfig=new DataConfig(gameRoot.element("data"));
	    			//创建系统配置对象
	    			systemConfig=new SystemConfig(gameRoot.element("system"));
	    		}else {
	    			//创建对象输入流,读取窗口配置
					ObjectInputStream read=new ObjectInputStream(new FileInputStream("data/frameConfig.dat"));
					frameConfig = (FrameConfig)read.readObject();
					//关闭流
					read.close();
					//读取数据配置
					read=new ObjectInputStream(new FileInputStream("data/dataConfig.dat"));
					dataConfig=(DataConfig)read.readObject();
					//关闭流
					read.close();
					//读取系统配置
					read=new ObjectInputStream(new FileInputStream("data/systemConfig.dat"));
					systemConfig=(SystemConfig)read.readObject();
					//关闭流
					read.close();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
         }

	public static FrameConfig getFrameConfig() {
		return frameConfig;
	}

	public static DataConfig getDataConfig() {
		return dataConfig;
	}

	public static SystemConfig getSystemConfig() {
		return systemConfig;
	}

}
